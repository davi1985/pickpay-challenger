package dev.davisilva.picpay.controller.dto;

import dev.davisilva.picpay.entity.Wallet;
import dev.davisilva.picpay.entity.WalletType;

public record CreateWalletDto(String fullName,
                              String cpfCnpj,
                              String email,
                              String password,
                              WalletType.Enum walletType) {

    public Wallet toWallet() {
        return new Wallet(
                fullName, cpfCnpj, email, password, walletType.get()
        );
    }
}
