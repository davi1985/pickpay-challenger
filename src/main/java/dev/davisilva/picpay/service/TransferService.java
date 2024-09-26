package dev.davisilva.picpay.service;

import dev.davisilva.picpay.controller.dto.TransferDTO;
import dev.davisilva.picpay.entity.Transfer;
import dev.davisilva.picpay.entity.Wallet;
import dev.davisilva.picpay.exception.InsufficientBalanceException;
import dev.davisilva.picpay.exception.TransferNotAllowedForWalledTypeException;
import dev.davisilva.picpay.exception.TransferNotAuthorizedException;
import dev.davisilva.picpay.exception.WalletNotFoundException;
import dev.davisilva.picpay.repository.TransferRepository;
import dev.davisilva.picpay.repository.WalletRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final AuthorizationService authorizationService;
    private final NotificationService notificationService;
    private final TransferRepository transferRepository;
    private final WalletRepository walletRepository;

    @Transactional
    public Transfer transfer(TransferDTO transferDTO) {
        var sender = walletRepository.findById(transferDTO.payer())
                .orElseThrow(() -> new WalletNotFoundException(transferDTO.payer()));

        var receiver = walletRepository.findById(transferDTO.payee())
                .orElseThrow(() -> new WalletNotFoundException(transferDTO.payee()));

        validateTransfer(transferDTO, sender);

        sender.debit(transferDTO.value());
        receiver.credit(transferDTO.value());


        var transfer = new Transfer(sender, receiver, transferDTO.value());

        walletRepository.save(receiver);
        walletRepository.save(sender);
        var transferResult = transferRepository.save(transfer);

        CompletableFuture.runAsync(() -> notificationService.sendNotification(transferResult));

        return transferResult;
    }

    private void validateTransfer(TransferDTO transferDTO, Wallet sender) {
        if (!sender.isTransferAllowedForWalledType()) {
            throw new TransferNotAllowedForWalledTypeException();
        }

        if (!sender.isBalancerEqualOrGreaterThan(transferDTO.value())) {
            throw new InsufficientBalanceException();
        }

        if (!authorizationService.isAuthorized(transferDTO)) {
            throw new TransferNotAuthorizedException();
        }
    }
}
