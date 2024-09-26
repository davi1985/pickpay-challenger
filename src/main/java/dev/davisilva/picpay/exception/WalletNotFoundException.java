package dev.davisilva.picpay.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

@Getter
@AllArgsConstructor
public class WalletNotFoundException extends PickPayException {

    private final Long walletId;

    @Override
    public ProblemDetail toProblemDetails() {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        problemDetail.setTitle("Wallet not found");
        problemDetail.setDetail("There is no wallet with id " + walletId);

        return problemDetail;
    }
}
