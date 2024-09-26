package dev.davisilva.picpay.exception;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.math.BigDecimal;

public class InsufficientBalanceException extends PickPayException {
    @Override
    public ProblemDetail toProblemDetails() {
        var problemDetail= ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        problemDetail.setTitle("Insufficient balance.");
        problemDetail.setDetail("You cannot transfer a value bigger than your current balance.");

        return problemDetail;
    }
}
