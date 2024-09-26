package dev.davisilva.picpay.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

@Getter
@AllArgsConstructor
public class WallerDataAlreadyExistsException extends PickPayException {

    private final String detail;

    @Override
    public ProblemDetail toProblemDetails() {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        problemDetail.setTitle("Wallet data already exists");
        problemDetail.setDetail(detail);

        return problemDetail;
    }
}
