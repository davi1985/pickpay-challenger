package dev.davisilva.picpay.controller;

import dev.davisilva.picpay.exception.PickPayException;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(PickPayException.class)
    public ProblemDetail handlePickPayException(PickPayException e) {
        return e.toProblemDetails();
    }
}
