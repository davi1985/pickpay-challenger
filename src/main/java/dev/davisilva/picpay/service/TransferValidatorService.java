package dev.davisilva.picpay.service;

import dev.davisilva.picpay.controller.dto.TransferDTO;
import dev.davisilva.picpay.entity.Wallet;
import dev.davisilva.picpay.exception.InsufficientBalanceException;
import dev.davisilva.picpay.exception.TransferNotAllowedForWalledTypeException;
import dev.davisilva.picpay.exception.TransferNotAuthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferValidatorService {

    private final AuthorizationService authorizationService;

    public void validate(TransferDTO transferDTO, Wallet sender) {
        isAuthorized(transferDTO);
        validateWalletType(sender);
        validateBalance(transferDTO, sender);
    }

    private void isAuthorized(TransferDTO transferDTO) {
        if (!authorizationService.isAuthorized(transferDTO)) {
            throw new TransferNotAuthorizedException();
        }
    }

    private static void validateBalance(TransferDTO transferDTO, Wallet sender) {
        if (!sender.isBalancerEqualOrGreaterThan(transferDTO.value())) {
            throw new InsufficientBalanceException();
        }
    }

    private void validateWalletType(Wallet sender) {
        if (!sender.isTransferAllowedForWalledType()) {
            throw new TransferNotAllowedForWalledTypeException();
        }
    }
}
