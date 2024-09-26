package dev.davisilva.picpay.service;

import dev.davisilva.picpay.client.AuthorizationClient;
import dev.davisilva.picpay.controller.dto.TransferDTO;
import dev.davisilva.picpay.exception.PickPayException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthorizationService {
    private final AuthorizationClient authorizationClient;

    public boolean isAuthorized(TransferDTO transfer) {
        var response = authorizationClient.isAuthorized();

        if (response.getStatusCode().isError()) {
            throw new PickPayException();
        }

        return Objects.requireNonNull(response.getBody()).authorized();
    }
}
