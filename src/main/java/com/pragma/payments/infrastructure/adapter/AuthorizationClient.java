package com.pragma.payments.infrastructure.adapter;

import com.pragma.payments.domain.model.PaymentRequest;
import com.pragma.payments.domain.port.AuthorizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
public class AuthorizationClient implements AuthorizationService {

    private static final Logger log = LoggerFactory.getLogger(AuthorizationClient.class);
    private static final String AUTHORIZATION_ENDPOINT = "http://localhost:8081/api/authorization/authorize";
    private static final String HEALTH_ENDPOINT = "http://localhost:8081/api/authorization/health";

    private final RestTemplate restTemplate;

    public AuthorizationClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Mono<String> authorizePayment(PaymentRequest paymentRequest) {
        log.info("Enviando solicitud de autorización para operationNumber={}, amount={} {}",
                paymentRequest.operationNumber(), paymentRequest.amount(), paymentRequest.currency());

        return Mono.fromCallable(() -> {
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);

                Map<String, Object> requestBody = new HashMap<>();
                requestBody.put("operationNumber", paymentRequest.operationNumber());
                requestBody.put("channel", paymentRequest.channel());
                requestBody.put("amount", paymentRequest.amount().toPlainString());
                requestBody.put("currency", paymentRequest.currency());
                requestBody.put("idempotencyKey", paymentRequest.idempotencyKey().toString());

                HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

                var response = restTemplate.postForEntity(
                        AUTHORIZATION_ENDPOINT,
                        request,
                        AuthorizationResponse.class
                );

                if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                    String result = response.getBody().getResult();
                    log.info("Autorización exitosa para operationNumber={}, resultado={}",
                            paymentRequest.operationNumber(), result);
                    return result;
                }

                log.error("Autorización fallida para operationNumber={}, status={}",
                        paymentRequest.operationNumber(), response.getStatusCode());
                throw new AuthorizationException("Autorización fallida con status: " + response.getStatusCode());

            } catch (Exception e) {
                log.error("Error en comunicación con motor de autorización para operationNumber={}: {}",
                        paymentRequest.operationNumber(), e.getMessage());
                throw new AuthorizationException("Error de comunicación con motor de autorización: " + e.getMessage(), e);
            }
        });
    }

    @Override
    public Mono<Boolean> isAvailable() {
        return Mono.fromCallable(() -> {
            try {
                var response = restTemplate.getForEntity(HEALTH_ENDPOINT, HealthResponse.class);
                boolean available = response.getStatusCode().is2xxSuccessful() &&
                        response.getBody() != null &&
                        "UP".equals(response.getBody().getStatus());
                log.debug("Estado del motor de autorización: {}", available ? "DISPONIBLE" : "NO DISPONIBLE");
                return available;
            } catch (Exception e) {
                log.warn("Motor de autorización no disponible: {}", e.getMessage());
                return false;
            }
        });
    }

    private static class AuthorizationResponse {
        private String result;
        private String message;

        public String getResult() {
            return result;
        }

        public void setResult(String result) {
            this.result = result;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    private static class HealthResponse {
        private String status;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static class AuthorizationException extends RuntimeException {
        public AuthorizationException(String message) {
            super(message);
        }

        public AuthorizationException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}