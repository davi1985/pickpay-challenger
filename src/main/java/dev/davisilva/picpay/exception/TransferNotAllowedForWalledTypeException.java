package dev.davisilva.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class TransferNotAllowedForWalledTypeException extends PickPayException {

    @Override
    public ProblemDetail toProblemDetails() {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        problemDetail.setTitle("This wallet type is not allowed to transfer.");

        return problemDetail;
    }
}
