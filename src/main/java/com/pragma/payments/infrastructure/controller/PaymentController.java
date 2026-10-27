package com.pragma.payments.infrastructure.controller;

import com.pragma.payments.application.PaymentService;
import com.pragma.payments.domain.model.PaymentRequest;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public Mono<ResponseEntity<PaymentRequest>> processPayment(
            @Valid @RequestBody PaymentRequest request) {
        
        logger.info("Recibida solicitud de pago - OperationNumber: {}, Channel: {}",
                request.operationNumber(), request.channel());
        
        return paymentService.processPayment(request)
                .map(payment -> ResponseEntity.status(HttpStatus.OK).body(payment))
                .onErrorResume(Exception.class, error -> {
                    logger.error("Error procesando pago: {}", error.getMessage(), error);
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }

    @GetMapping("/{operationNumber}")
    public Mono<ResponseEntity<PaymentRequest>> getPaymentByOperationNumber(
            @PathVariable String operationNumber,
            @RequestParam String channel) {
        
        logger.info("Consultando pago - OperationNumber: {}, Channel: {}", operationNumber, channel);
        
        return paymentService.findByOperationNumberAndChannel(operationNumber, channel)
                .map(payment -> ResponseEntity.ok(payment))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/health")
    public Mono<ResponseEntity<String>> healthCheck() {
        return paymentService.checkServiceAvailability()
                .flatMap(available -> {
                    if (Boolean.TRUE.equals(available)) {
                        return Mono.just(ResponseEntity.ok("OK"));
                    }
                    return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build());
                });
    }
}