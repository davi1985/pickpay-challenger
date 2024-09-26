package dev.davisilva.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TransferNotAuthorizedException extends PickPayException {

    @Override
    public ProblemDetail toProblemDetails() {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        problemDetail.setTitle("Transfer not authorized.");
        problemDetail.setDetail("Authorization Service not authorized this transfer.");

        return problemDetail;
    }
}
