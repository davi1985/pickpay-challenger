package dev.davisilva.picpay.service;

import dev.davisilva.picpay.controller.dto.CreateWalletDto;
import dev.davisilva.picpay.entity.Wallet;
import dev.davisilva.picpay.repository.WalletRepository;
import org.springframework.stereotype.Service;

@Service
public class WalletService {
    private WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet create(CreateWalletDto dto) {
        return walletRepository.save(dto.toWallet());
    }
}
