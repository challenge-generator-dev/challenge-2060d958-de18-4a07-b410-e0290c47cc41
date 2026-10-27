package com.pragma.payments.domain.port;

import com.pragma.payments.domain.model.PaymentRequest;
import reactor.core.publisher.Mono;

public interface AuthorizationService {
    Mono<String> authorizePayment(PaymentRequest paymentRequest);
    
    Mono<Boolean> isAvailable();
}