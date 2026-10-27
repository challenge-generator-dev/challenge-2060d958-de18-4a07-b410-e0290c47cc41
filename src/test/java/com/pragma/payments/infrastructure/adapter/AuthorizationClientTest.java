package com.pragma.payments.infrastructure.adapter;

import com.pragma.payments.domain.model.PaymentRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("unit")
@DisplayName("AuthorizationClient - Tests de comunicación con el motor de autorización")
class AuthorizationClientTest {

    @Mock
    private RestTemplate restTemplate;

    private AuthorizationClient authorizationClient;
    private PaymentRequest paymentRequest;
    private static final String AUTHORIZATION_URL = "http://authorization-service:8080/api/authorize";
    private static final String HEALTH_URL = "http://authorization-service:8080/api/health";

    @BeforeEach
    void setUp() {
        authorizationClient = new AuthorizationClient(restTemplate, AUTHORIZATION_URL);
        paymentRequest = new PaymentRequest(
            "OP-2024-001",
            "WEB",
            new BigDecimal("1000.00"),
            "USD",
            UUID.randomUUID().toString(),
            LocalDateTime.now(),
            null
        );
    }

    @Test
    @DisplayName("Debe retornar AUTHORIZED cuando el servicio responde 200 OK")
    void debeRetornarAuthorizedCuandoRespuesta200() {
        when(restTemplate.postForEntity(eq(AUTHORIZATION_URL), any(PaymentRequest.class), eq(String.class)))
            .thenReturn(new ResponseEntity<>("AUTHORIZED", HttpStatus.OK));

        Mono<String> result = authorizationClient.authorizePayment(paymentRequest);

        StepVerifier.create(result)
            .expectNext("AUTHORIZED")
            .verifyComplete();
    }

    @Test
    @DisplayName("Debe retornar DECLINED cuando el servicio responde 400 Bad Request")
    void debeRetornarDeclinedCuandoRespuesta400() {
        when(restTemplate.postForEntity(eq(AUTHORIZATION_URL), any(PaymentRequest.class), eq(String.class)))
            .thenReturn(new ResponseEntity<>("DECLINED", HttpStatus.BAD_REQUEST));

        Mono<String> result = authorizationClient.authorizePayment(paymentRequest);

        StepVerifier.create(result)
            .expectNext("DECLINED")
            .verifyComplete();
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el servicio responde 500 Internal Server Error")
    void debeLanzarExcepcionCuandoRespuesta500() {
        when(restTemplate.postForEntity(eq(AUTHORIZATION_URL), any(PaymentRequest.class), eq(String.class)))
            .thenReturn(new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR));

        Mono<String> result = authorizationClient.authorizePayment(paymentRequest);

        StepVerifier.create(result)
            .expectErrorMatches(e -> e.getMessage().contains("500"))
            .verify();
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el servicio responde 503 Service Unavailable")
    void debeLanzarExcepcionCuandoRespuesta503() {
        when(restTemplate.postForEntity(eq(AUTHORIZATION_URL), any(PaymentRequest.class), eq(String.class)))
            .thenReturn(new ResponseEntity<>("Service Unavailable", HttpStatus.SERVICE_UNAVAILABLE));

        Mono<String> result = authorizationClient.authorizePayment(paymentRequest);

        StepVerifier.create(result)
            .expectErrorMatches(e -> e.getMessage().contains("503"))
            .verify();
    }

    @Test
    @DisplayName("Debe retornar true cuando el servicio de salud responde 200 OK")
    void debeRetornarTrueCuandoHealth200() {
        when(restTemplate.getForEntity((eq(HEALTH_URL)), eq(String.class)))
            .thenReturn(new ResponseEntity<>("OK", HttpStatus.OK));

        Mono<Boolean> result = authorizationClient.isAvailable();

        StepVerifier.create(result)
            .expectNext(true)
            .verifyComplete();
    }

    @Test
    @DisplayName("Debe retornar false cuando el servicio de salud responde 503")
    void debeRetornarFalseCuandoHealth503() {
        when(restTemplate.getForEntity(eq(HEALTH_URL), eq(String.class)))
            .thenReturn(new ResponseEntity<>("UNAVAILABLE", HttpStatus.SERVICE_UNAVAILABLE));

        Mono<Boolean> result = authorizationClient.isAvailable();

        StepVerifier.create(result)
            .expectNext(false)
            .verifyComplete();
    }

    @Test
    @DisplayName("Debe lanzar excepción cuando el servicio de salud no responde")
    void debeLanzarExcepcionCuandoHealthNoResponde() {
        when(restTemplate.getForEntity(eq(HEALTH_URL), eq(String.class)))
            .thenThrow(new RuntimeException("Connection refused"));

        Mono<Boolean> result = authorizationClient.isAvailable();

        StepVerifier.create(result)
            .expectError(RuntimeException.class)
            .verify();
    }

    @Test
    @DisplayName("Debe mapear correctamente los datos del PaymentRequest al enviar al servicio de autorización")
    void debeMapearDatosCorrectamente() {
        when(restTemplate.postForEntity(eq(AUTHORIZATION_URL), any(PaymentRequest.class), eq(String.class)))
            .thenReturn(new ResponseEntity<>("AUTHORIZED", HttpStatus.OK));

        PaymentRequest request = new PaymentRequest(
            "OP-TEST-999",
            "MOBILE",
            new BigDecimal("5000.00"),
            "EUR",
            UUID.randomUUID().toString(),
            LocalDateTime.now(),
            null
        );

        Mono<String> result = authorizationClient.authorizePayment(request);

        StepVerifier.create(result)
            .expectNext("AUTHORIZED")
            .verifyComplete();
    }

    @Test
    @DisplayName("Debe manejar respuesta con código 401 Unauthorized")
    void debeManejar401Unauthorized() {
        when(restTemplate.postForEntity(eq(AUTHORIZATION_URL), any(PaymentRequest.class), eq(String.class)))
            .thenReturn(new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED));

        Mono<String> result = authorizationClient.authorizePayment(paymentRequest);

        StepVerifier.create(result)
            .expectErrorMatches(e -> e.getMessage().contains("401"))
            .verify();
    }

    @Test
    @DisplayName("Debe manejar respuesta con código 403 Forbidden")
    void debeManejar403Forbidden() {
        when(restTemplate.postForEntity(eq(AUTHORIZATION_URL), any(PaymentRequest.class), eq(String.class)))
            .thenReturn(new ResponseEntity<>("Forbidden", HttpStatus.FORBIDDEN));

        Mono<String> result = authorizationClient.authorizePayment(paymentRequest);

        StepVerifier.create(result)
            .expectErrorMatches(e -> e.getMessage().contains("403"))
            .verify();
    }
}