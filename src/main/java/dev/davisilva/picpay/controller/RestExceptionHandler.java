package dev.davisilva.picpay.controller;

import dev.davisilva.picpay.exception.PickPayException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(PickPayException.class)
    public ProblemDetail handlePickPayException(PickPayException e) {
        return e.toProblemDetails();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var fieldsErrors = e.getFieldErrors()
                .stream()
                .map(field -> new InvalidParam(field.getField(), field.getDefaultMessage()))
                .toList();

        var problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Your request parameter didn't validate.");
        problemDetail.setProperty("invalid-params", fieldsErrors);

        return problemDetail;
    }

    private record InvalidParam(String name, String reason) {
    }
}
