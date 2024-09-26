package dev.davisilva.picpay.service;

import dev.davisilva.picpay.controller.dto.TransferDTO;
import dev.davisilva.picpay.entity.Transfer;
import dev.davisilva.picpay.entity.Wallet;
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

    private final NotificationService notificationService;
    private final TransferRepository transferRepository;
    private final WalletRepository walletRepository;
    private final TransferValidatorService transferValidatorService;

    @Transactional
    public Transfer transfer(TransferDTO transferDTO) {
        Wallet sender = findWallet(transferDTO.payer());
        Wallet receiver = findWallet(transferDTO.payee());

        transferValidatorService.validate(transferDTO, sender);
        executeTransfer(transferDTO, sender, receiver);

        Transfer transfer = createTransfer(transferDTO, sender, receiver);

        CompletableFuture.runAsync(() -> notificationService.sendNotification(transfer));

        return transfer;
    }

    private Transfer createTransfer(TransferDTO transferDTO, Wallet sender, Wallet receiver) {
        Transfer transfer = new Transfer(sender, receiver, transferDTO.value());

        return transferRepository.save(transfer);
    }


    private void executeTransfer(TransferDTO transferDTO, Wallet sender, Wallet receiver) {
        sender.debit(transferDTO.value());
        receiver.credit(transferDTO.value());

        walletRepository.save(receiver);
        walletRepository.save(sender);
    }

    private Wallet findWallet(Long walletId) {
        return walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException(walletId));
    }

}
