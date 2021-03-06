package com.ataide.corona.coronatracker.application.controller;

import com.ataide.corona.coronatracker.application.dtos.response.ResponseError;
import com.ataide.corona.coronatracker.application.dtos.response.Response;
import com.ataide.corona.coronatracker.application.exceptions.MissingRequiredParameterException;
import com.ataide.corona.coronatracker.application.exceptions.UsernameAllocatedException;
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

    @ResponseBody
    @ExceptionHandler(TransactionSystemException.class)
    protected Response handleConstraintViolationException(TransactionSystemException ex) {
        logger.error("Handle constraint violation exception - {1}");
        ex.printStackTrace();
        List<String> errors;
        if (ex.getRootCause() instanceof ConstraintViolationException) {
            ConstraintViolationException constraintViolationException = (ConstraintViolationException) ex.getRootCause();
            Set<ConstraintViolation<?>> constraintViolations = constraintViolationException.getConstraintViolations();
            errors = constraintViolations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        } else {
            errors = Collections.singletonList("Houve um erro inesperado.");
        }
        return new ResponseError(HttpStatus.BAD_REQUEST.value(), errors, LocalDateTime.now());
    }

    @ResponseBody
    @ExceptionHandler(value = { UsernameAllocatedException.class, MissingRequiredParameterException.class })
    protected Response handleUsernameAllocatedException(UsernameAllocatedException ex) {
        logger.error("Handle {}", ex.getClass().getName());
        return new ResponseError(HttpStatus.BAD_REQUEST.value(), List.of(ex.getMessage()), LocalDateTime.now());
    }

//    @ResponseBody
//    @ExceptionHandler(value = {MissingRequiredParameterException.class})
//    protected Response handleMissingRequiredParameterException(MissingRequiredParameterException ex) {
//        logger.error("Handle MissingRequiredParameterException exception");
//
//        return new ResponseError(HttpStatus.BAD_REQUEST.value(), List.of(ex.getMessage()), LocalDateTime.now());
//    }


//    return Response.builder()
//            .statusCode(HttpStatus.CREATED.value())
//            .data(null)
//                .timestamp(LocalDateTime.now())
//            .build();
}
