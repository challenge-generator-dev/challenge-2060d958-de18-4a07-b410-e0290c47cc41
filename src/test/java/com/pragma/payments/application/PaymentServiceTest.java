package com.pragma.payments.application;

import com.pragma.payments.domain.model.PaymentRequest;
import com.pragma.payments.domain.port.AuthorizationService;
import com.pragma.payments.domain.port.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Tag("unit")
@DisplayName("PaymentService - Tests de lógica de reintentos y procesamiento de pagos")
class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private AuthorizationService authorizationService;

    @InjectMocks
    private PaymentService paymentService;

    private PaymentRequest paymentRequest;
    private static final String OPERATION_NUMBER = "OP-2024-001";
    private static final String CHANNEL = "WEB";
    private static final String IDEMPOTENCY_KEY = "idem-key-123";

    @BeforeEach
    void setUp() {
        paymentRequest = new PaymentRequest(
            OPERATION_NUMBER,
            CHANNEL,
            new BigDecimal("1000.00"),
            "USD",
            IDEMPOTENCY_KEY,
            LocalDateTime.now(),
            null
        );
    }

    @Test
    @DisplayName("Debe procesar pago exitosamente en primer intento cuando el servicio de autorización responde OK")
    void debeProcesarPagoExitosamenteEnPrimerIntento() {
        when(authorizationService.authorizePayment(any(PaymentRequest.class)))
            .thenReturn(Mono.just("AUTHORIZED"));
        when(paymentRepository.existsByOperationNumberAndChannel(OPERATION_NUMBER, CHANNEL))
            .thenReturn(false);
        when(paymentRepository.save(any(PaymentRequest.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

        Mono<PaymentRequest> result = paymentService.processPayment(paymentRequest);

        StepVerifier.create(result)
            .expectNextMatches(payment -> "AUTHORIZED".equals(payment.authorizationResult()))
            .verifyComplete();

        verify(authorizationService, times(1)).authorizePayment(any(PaymentRequest.class));
        verify(paymentRepository, times(1)).save(any(PaymentRequest.class));
    }

    @Test
    @DisplayName("Debe reintentar hasta 3 veces cuando el servicio de autorización retorna error 5xx")
    void debeReintentarHasta3VecesEnError5xx() {
        when(authorizationService.authorizePayment(any(PaymentRequest.class)))
            .thenReturn(
                Mono.error(new RuntimeException("500 Internal Server Error")),
                Mono.error(new RuntimeException("503 Service Unavailable")),
                Mono.just("AUTHORIZED")
            );
        when(paymentRepository.existsByOperationNumberAndChannel(OPERATION_NUMBER, CHANNEL))
            .thenReturn(false);
        when(paymentRepository.save(any(PaymentRequest.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

        Mono<PaymentRequest> result = paymentService.processPayment(paymentRequest);

        StepVerifier.create(result)
            .expectNextMatches(payment -> "AUTHORIZED".equals(payment.authorizationResult()))
            .verifyComplete();

        verify(authorizationService, times(3)).authorizePayment(any(PaymentRequest.class));
    }

    @Test
    @DisplayName("Debe fallar después de 3 reintentos cuando el servicio de autorización sigue fallando")
    void debeFallarDespuesDe3Reintentos() {
        when(authorizationService.authorizePayment(any(PaymentRequest.class)))
            .thenReturn(
                Mono.error(new RuntimeException("500 Internal Server Error")),
                Mono.error(new RuntimeException("503 Service Unavailable")),
                Mono.error(new RuntimeException("502 Bad Gateway")),
                Mono.error(new RuntimeException("504 Gateway Timeout"))
            );
        when(paymentRepository.existsByOperationNumberAndChannel(OPERATION_NUMBER, CHANNEL))
            .thenReturn(false);

        Mono<PaymentRequest> result = paymentService.processPayment(paymentRequest);

        StepVerifier.create(result)
            .expectError(RuntimeException.class)
            .verify();

        verify(authorizationService, times(3)).authorizePayment(any(PaymentRequest.class));
    }

    @Test
    @DisplayName("Debe rechazar pago por idempotencia si ya existe operación con mismo número y canal")
    void debeRechazarPagoPorIdempotencia() {
        when(paymentRepository.existsByOperationNumberAndChannel(OPERATION_NUMBER, CHANNEL))
            .thenReturn(true);
        when(paymentRepository.findByOperationNumberAndChannel(OPERATION_NUMBER, CHANNEL))
            .thenReturn(Optional.of(paymentRequest.withAuthorizationResult("AUTHORIZED")));

        Mono<PaymentRequest> result = paymentService.processPayment(paymentRequest);

        StepVerifier.create(result)
            .expectNextMatches(payment -> "AUTHORIZED".equals(payment.authorizationResult()))
            .verifyComplete();

        verify(authorizationService, never()).authorizePayment(any(PaymentRequest.class));
        verify(paymentRepository, never()).save(any(PaymentRequest.class));
    }

    @Test
    @DisplayName("Debe verificar idempotencia por clave antes de procesar")
    void debeVerificarIdempotenciaPorClave() {
        String anotherIdempotencyKey = UUID.randomUUID().toString();
        PaymentRequest duplicateRequest = new PaymentRequest(
            OPERATION_NUMBER,
            CHANNEL,
            new BigDecimal("1000.00"),
            "USD",
            anotherIdempotencyKey,
            LocalDateTime.now(),
            null
        );

        when(paymentRepository.findByIdempotencyKey(UUID.fromString(anotherIdempotencyKey)))
            .thenReturn(Optional.of(paymentRequest.withAuthorizationResult("AUTHORIZED")));

        Mono<PaymentRequest> result = paymentService.processPayment(duplicateRequest);

        StepVerifier.create(result)
            .expectNextMatches(payment -> "AUTHORIZED".equals(payment.authorizationResult()))
            .verifyComplete();

        verify(paymentRepository, never()).existsByOperationNumberAndChannel(anyString(), anyString());
    }

    @Test
    @DisplayName("Debe guardar pago con resultado de autorización exitoso")
    void debeGuardarPagoConResultadoExitoso() {
        when(authorizationService.authorizePayment(any(PaymentRequest.class)))
            .thenReturn(Mono.just("AUTHORIZED"));
        when(paymentRepository.existsByOperationNumberAndChannel(OPERATION_NUMBER, CHANNEL))
            .thenReturn(false);
        when(paymentRepository.save(any(PaymentRequest.class)))
            .thenAnswer(invocation -> {
                PaymentRequest saved = invocation.getArgument(0);
                return new PaymentRequest(
                    saved.operationNumber(),
                    saved.channel(),
                    saved.amount(),
                    saved.currency(),
                    saved.idempotencyKey(),
                    saved.requestDate(),
                    saved.authorizationResult()
                );
            });

        Mono<PaymentRequest> result = paymentService.processPayment(paymentRequest);

        StepVerifier.create(result)
            .expectNextMatches(payment -> payment.authorizationResult() != null)
            .verifyComplete();

        verify(paymentRepository).save(any(PaymentRequest.class));
    }

    @Test
    @DisplayName("Debe manejar error de autorización con resultado DECLINED")
    void debeManejarErrorAutorizacion() {
        when(authorizationService.authorizePayment(any(PaymentRequest.class)))
            .thenReturn(Mono.just("DECLINED"));
        when(paymentRepository.existsByOperationNumberAndChannel(OPERATION_NUMBER, CHANNEL))
            .thenReturn(false);
        when(paymentRepository.save(any(PaymentRequest.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

        Mono<PaymentRequest> result = paymentService.processPayment(paymentRequest);

        StepVerifier.create(result)
            .expectNextMatches(payment -> "DECLINED".equals(payment.authorizationResult()))
            .verifyComplete();

        verify(paymentRepository).save(any(PaymentRequest.class));
    }

    @Test
    @DisplayName("Debe verificar disponibilidad del servicio de autorización antes de procesar")
    void debeVerificarDisponibilidadDelServicio() {
        when(authorizationService.isAvailable())
            .thenReturn(Mono.just(true));
        when(authorizationService.authorizePayment(any(PaymentRequest.class)))
            .thenReturn(Mono.just("AUTHORIZED"));
        when(paymentRepository.existsByOperationNumberAndChannel(OPERATION_NUMBER, CHANNEL))
            .thenReturn(false);
        when(paymentRepository.save(any(PaymentRequest.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

        Mono<PaymentRequest> result = paymentService.processPayment(paymentRequest);

        StepVerifier.create(result)
            .expectNextCount(1)
            .verifyComplete();

        verify(authorizationService).isAvailable();
    }

    @Test
    @DisplayName("Debe rechazar cuando el servicio de autorización no está disponible")
    void debeRechazarCuandoServicioNoDisponible() {
        when(authorizationService.isAvailable())
            .thenReturn(Mono.just(false));

        Mono<PaymentRequest> result = paymentService.processPayment(paymentRequest);

        StepVerifier.create(result)
            .expectErrorMatches(e -> e.getMessage().contains("Authorization service unavailable"))
            .verify();

        verify(authorizationService, never()).authorizePayment(any(PaymentRequest.class));
    }
}