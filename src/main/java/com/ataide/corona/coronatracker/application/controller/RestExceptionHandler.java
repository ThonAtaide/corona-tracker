package com.ataide.corona.coronatracker.application.controller;

import com.ataide.corona.coronatracker.application.dtos.response.ErrorRecord;
import com.ataide.corona.coronatracker.application.dtos.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(TransactionSystemException.class)
    @ResponseBody
    protected Response handleConstraintViolationException(TransactionSystemException ex) {
        logger.error("Handle constraint violation exception - {}");
        ex.printStackTrace();
        List<String> errors;
        if (ex.getRootCause() instanceof ConstraintViolationException) {
            ConstraintViolationException constraintViolationException = (ConstraintViolationException) ex.getRootCause();
            Set<ConstraintViolation<?>> constraintViolations = constraintViolationException.getConstraintViolations();
            errors = constraintViolations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        } else {
            errors = Collections.singletonList("Houve um erro inesperado.");
        }
        ErrorRecord errorRecord = new ErrorRecord(errors);
        return new Response(HttpStatus.BAD_REQUEST.value(), errorRecord, LocalDateTime.now());
    }
}
