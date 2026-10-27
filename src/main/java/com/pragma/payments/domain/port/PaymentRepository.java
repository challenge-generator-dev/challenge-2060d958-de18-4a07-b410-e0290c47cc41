package com.pragma.payments.domain.port;

import com.pragma.payments.domain.model.PaymentRequest;
import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository {
    PaymentRequest save(PaymentRequest paymentRequest);
    
    Optional<PaymentRequest> findByOperationNumberAndChannel(String operationNumber, String channel);
    
    Optional<PaymentRequest> findByIdempotencyKey(UUID idempotencyKey);
    
    boolean existsByOperationNumberAndChannel(String operationNumber, String channel);
}