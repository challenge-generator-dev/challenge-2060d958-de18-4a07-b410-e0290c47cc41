package com.pragma.payments.application;

import com.pragma.payments.domain.model.PaymentRequest;
import com.pragma.payments.domain.port.AuthorizationService;
import com.pragma.payments.domain.port.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);
    private static final int MAX_RETRY_ATTEMPTS = 3;
    private static final long RETRY_INTERVAL_SECONDS = 5;

    private final PaymentRepository paymentRepository;
    private final AuthorizationService authorizationService;

    public PaymentService(PaymentRepository paymentRepository, AuthorizationService authorizationService) {
        this.paymentRepository = paymentRepository;
        this.authorizationService = authorizationService;
    }

    public Mono<PaymentRequest> processPayment(PaymentRequest request) {
        log.info("Iniciando procesamiento de pago para operationNumber={}, channel={}, amount={} {}",
                request.operationNumber(), request.channel(), request.amount(), request.currency());

        return checkIdempotency(request.idempotencyKey())
                .flatMap(exists -> {
                    if (exists) {
                        log.warn("Solicitud duplicada detectada para idempotencyKey={}", request.idempotencyKey());
                        return paymentRepository.findByIdempotencyKey(request.idempotencyKey())
                                .map(Mono::just)
                                .orElseGet(() -> Mono.error(new IllegalStateException("Inconsistencia en idempotencia")));
                    }
                    return processWithRetry(request);
                });
    }

    private Mono<Boolean> checkIdempotency(UUID idempotencyKey) {
        return Mono.fromCallable(() -> paymentRepository.findByIdempotencyKey(idempotencyKey).isPresent())
                .onErrorResume(e -> {
                    log.error("Error al verificar idempotencia: {}", e.getMessage());
                    return Mono.just(false);
                });
    }

    private Mono<PaymentRequest> processWithRetry(PaymentRequest request) {
        return authorizationService.authorizePayment(request)
                .retryWhen(Retry.backoff(MAX_RETRY_ATTEMPTS, Duration.ofSeconds(RETRY_INTERVAL_SECONDS))
                        .filter(throwable -> isRetryableError(throwable))
                        .doBeforeRetry(retrySignal -> {
                            log.warn("Reintento {} de {} para operationNumber={}",
                                    retrySignal.totalRetries() + 1,
                                    MAX_RETRY_ATTEMPTS,
                                    request.operationNumber());
                        }))
                .flatMap(authResult -> {
                    PaymentRequest updatedRequest = request.withAuthorizationResult(authResult);
                    return savePayment(updatedRequest);
                })
                .onErrorResume(e -> {
                    log.error("Error final en procesamiento de pago para operationNumber={}: {}",
                            request.operationNumber(), e.getMessage());
                    return Mono.error(e);
                });
    }

    private boolean isRetryableError(Throwable throwable) {
        String message = throwable.getMessage();
        if (message == null) {
            return false;
        }
        return message.contains("5") || 
               message.contains("500") || 
               message.contains("502") || 
               message.contains("503") ||
               message.contains("504") ||
               message.toLowerCase().contains("server error");
    }

    private Mono<PaymentRequest> savePayment(PaymentRequest request) {
        PaymentRequest requestToSave = new PaymentRequest(
                request.operationNumber(),
                request.channel(),
                request.amount(),
                request.currency(),
                request.idempotencyKey(),
                request.authorizationResult(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        return Mono.fromCallable(() -> paymentRepository.save(requestToSave))
                .doOnSuccess(saved -> log.info("Pago procesado exitosamente para operationNumber={}, resultado={}",
                        saved.operationNumber(), saved.authorizationResult()))
                .doOnError(e -> log.error("Error al guardar pago: {}", e.getMessage()));
    }

    public Mono<PaymentRequest> getPaymentByOperationNumber(String operationNumber, String channel) {
        return Mono.fromCallable(() -> paymentRepository.findByOperationNumberAndChannel(operationNumber, channel))
                .flatMap(opt -> opt.map(Mono::just).orElseGet(() -> Mono.empty()));
    }
}