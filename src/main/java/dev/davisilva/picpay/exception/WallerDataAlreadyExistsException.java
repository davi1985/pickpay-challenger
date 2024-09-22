package dev.davisilva.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class WallerDataAlreadyExistsException extends PickPayException {

    private String detail;

    public WallerDataAlreadyExistsException(String detail) {
        this.detail = detail;
    }

    @Override
    public ProblemDetail toProblemDetails() {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        problemDetail.setTitle("Wallet data already exists");
        problemDetail.setDetail(detail);

        return problemDetail;
    }
}
