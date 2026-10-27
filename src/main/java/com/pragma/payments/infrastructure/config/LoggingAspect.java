package com.pragma.payments.infrastructure.config;



import com.pragma.payments.application.PaymentService;
import com.pragma.payments.infrastructure.controller.PaymentController;
import com.pragma.payments.domain.model.PaymentRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Before("execution(* com.pragma.payments.infrastructure.controller.PaymentController.*(..))")
    public void logBeforePaymentRequest(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        
        logger.info("INICIO DE SOLICITUD - Método: {}", methodName);
        
        for (Object arg : args) {
            if (arg instanceof PaymentRequest request) {
                logger.info("DATOS SOLICITUD - Número de operación: {}, Canal: {}, Monto: {}, Moneda: {}",
                        request.operationNumber(),
                        request.channel(),
                        request.amount(),
                        request.currency());
            } else if (arg instanceof String) {
                logger.debug("Parámetro: {}", arg);
            }
        }
    }

    @AfterReturning(pointcut = "execution(* com.pragma.payments.infrastructure.controller.PaymentController.*(..))",
                    returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String timestamp = LocalDateTime.now().format(DATE_TIME_FORMATTER);
        
        logger.info("RESULTADO EXITOSO - Método: {}, Timestamp: {}, Resultado: {}",
                methodName, timestamp, result);
        
        if (result instanceof PaymentRequest request) {
            logger.info("RESPUESTA PROCESADA - Número de operación: {}, Estado: {}, Autorización: {}",
                    request.operationNumber(),
                    request.status(),
                    request.authorizationResult());
        }
    }

    @AfterThrowing(pointcut = "execution(* com.pragma.payments.infrastructure.controller.PaymentController.*(..))",
                   throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getName();
        String timestamp = LocalDateTime.now().format(DATE_TIME_FORMATTER);
        
        logger.error("ERROR EN SOLICITUD - Método: {}, Timestamp: {}, Tipo de error: {}, Mensaje: {}",
                methodName, timestamp, exception.getClass().getSimpleName(), exception.getMessage());
        
        logger.error("Stack trace completo:", exception);
    }

    @Before("execution(* com.pragma.payments.application.PaymentService.*(..))")
    public void logServiceLayer(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        
        logger.debug("EJECUTANDO SERVICIO - Clase: {}, Método: {}, Argumentos: {}",
                className, methodName, Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "execution(* com.pragma.payments.application.PaymentService.*(..))",
                    returning = "result")
    public void logServiceLayerComplete(JoinPoint joinPoint, Object result) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        
        logger.debug("SERVICIO COMPLETADO - Clase: {}, Método: {}, Resultado tipo: {}",
                className, methodName, result != null ? result.getClass().getSimpleName() : "void");
    }
}