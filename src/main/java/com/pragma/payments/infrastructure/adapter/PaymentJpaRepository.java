package com.pragma.payments.infrastructure.adapter;

import com.pragma.payments.domain.model.PaymentRequest;
import com.pragma.payments.domain.port.PaymentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class PaymentJpaRepository implements PaymentRepository {

    private final PaymentRequestJpaRepository jpaRepository;

    public PaymentJpaRepository(PaymentRequestJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public PaymentRequest save(PaymentRequest paymentRequest) {
        PaymentRequestEntity entity = toEntity(paymentRequest);
        PaymentRequestEntity savedEntity = jpaRepository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<PaymentRequest> findByOperationNumberAndChannel(String operationNumber, String channel) {
        return jpaRepository.findByOperationNumberAndChannel(operationNumber, channel)
                .map(this::toDomain);
    }

    @Override
    public Optional<PaymentRequest> findByIdempotencyKey(UUID idempotencyKey) {
        return jpaRepository.findByIdempotencyKey(idempotencyKey)
                .map(this::toDomain);
    }

    @Override
    public boolean existsByOperationNumberAndChannel(String operationNumber, String channel) {
        return jpaRepository.existsByOperationNumberAndChannel(operationNumber, channel);
    }

    private PaymentRequestEntity toEntity(PaymentRequest domain) {
        PaymentRequestEntity entity = new PaymentRequestEntity();
        entity.setOperationNumber(domain.operationNumber());
        entity.setChannel(domain.channel());
        entity.setAmount(domain.amount());
        entity.setCurrency(domain.currency());
        entity.setIdempotencyKey(domain.idempotencyKey());
        entity.setAuthorizationResult(domain.authorizationResult());
        entity.setCreatedAt(domain.createdAt());
        entity.setUpdatedAt(domain.updatedAt());
        return entity;
    }

    private PaymentRequest toDomain(PaymentRequestEntity entity) {
        return new PaymentRequest(
                entity.getOperationNumber(),
                entity.getChannel(),
                entity.getAmount(),
                entity.getCurrency(),
                entity.getIdempotencyKey(),
                entity.getAuthorizationResult(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public interface PaymentRequestJpaRepository extends JpaRepository<PaymentRequestEntity, Long> {
        Optional<PaymentRequestEntity> findByOperationNumberAndChannel(String operationNumber, String channel);

        Optional<PaymentRequestEntity> findByIdempotencyKey(UUID idempotencyKey);

        boolean existsByOperationNumberAndChannel(String operationNumber, String channel);
    }
}