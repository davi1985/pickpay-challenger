package dev.davisilva.picpay.service;

import dev.davisilva.picpay.client.AuthorizationClient;
import dev.davisilva.picpay.controller.dto.TransferDTO;
import dev.davisilva.picpay.exception.PickPayException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {
    private final AuthorizationClient authorizationClient;

    public AuthorizationService(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    public boolean isAuthorized(TransferDTO transfer) {
        var response = authorizationClient.isAuthorized();

        if (response.getStatusCode().isError()) {
            throw new PickPayException();
        }

        return response.getBody().authorized();
    }
}
