package com.pragma.payments.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentRequest(
    @NotBlank(message = "El número de operación no puede estar vacío")
    @Pattern(regexp = "^[A-Za-z0-9-]{1,50}$", message = "El número de operación debe ser alfanumérico y tener entre 1 y 50 caracteres")
    String operationNumber,
    
    @NotBlank(message = "El canal no puede estar vacío")
    @Pattern(regexp = "^[A-Z]{2,10}$", message = "El canal debe ser una cadena de 2 a 10 caracteres en mayúsculas")
    String channel,
    
    @NotNull(message = "La fecha y hora no pueden ser nulas")
    LocalDateTime requestDateTime,
    
    @NotNull(message = "El monto no puede ser nulo")
    @Positive(message = "El monto debe ser positivo")
    BigDecimal amount,
    
    @NotBlank(message = "La moneda no puede estar vacía")
    @Pattern(regexp = "^[A-Z]{3}$", message = "La moneda debe ser un código de 3 letras en mayúsculas")
    String currency,
    
    String merchantId,
    
    @NotBlank(message = "El número de tarjeta no puede estar vacío")
    @Pattern(regexp = "^\\d{13,19}$", message = "El número de tarjeta debe tener entre 13 y 19 dígitos")
    String cardNumber,
    
    String authorizationResult,
    
    UUID idempotencyKey
) {
    public PaymentRequest {
        if (operationNumber == null || operationNumber.isBlank()) {
            throw new IllegalArgumentException("El número de operación no puede ser nulo o vacío");
        }
        if (channel == null || channel.isBlank()) {
            throw new IllegalArgumentException("El canal no puede ser nulo o vacío");
        }
        if (requestDateTime == null) {
            throw new IllegalArgumentException("La fecha y hora no pueden ser nulas");
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser positivo");
        }
        if (currency == null || currency.isBlank()) {
            throw new IllegalArgumentException("La moneda no puede ser nula o vacía");
        }
        if (cardNumber == null || cardNumber.isBlank()) {
            throw new IllegalArgumentException("El número de tarjeta no puede ser nulo o vacío");
        }
        if (idempotencyKey == null) {
            idempotencyKey = UUID.randomUUID();
        }
    }
    
    public PaymentRequest withAuthorizationResult(String result) {
        return new PaymentRequest(
            this.operationNumber,
            this.channel,
            this.requestDateTime,
            this.amount,
            this.currency,
            this.merchantId,
            this.cardNumber,
            result,
            this.idempotencyKey
        );
    }
}