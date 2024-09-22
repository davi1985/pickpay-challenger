package dev.davisilva.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class PickPayException extends RuntimeException {

    public ProblemDetail toProblemDetails() {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        problemDetail.setTitle("PickPay internal server error");

        return problemDetail;
    }
}
