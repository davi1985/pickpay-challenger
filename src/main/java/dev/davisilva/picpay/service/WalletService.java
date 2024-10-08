package dev.davisilva.picpay.service;

import dev.davisilva.picpay.controller.dto.CreateWalletDto;
import dev.davisilva.picpay.entity.Wallet;
import dev.davisilva.picpay.exception.WallerDataAlreadyExistsException;
import dev.davisilva.picpay.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;

    public Wallet create(CreateWalletDto dto) {
        var walletByCpfCnpjOrEmailAlreadyExists = walletRepository.findByCpfCnpjOrEmail(dto.cpfCnpj(), dto.email());

        if (walletByCpfCnpjOrEmailAlreadyExists.isPresent()) {
            throw new WallerDataAlreadyExistsException("CpfCnpj or Email already exists");
        }

        return walletRepository.save(dto.toWallet());
    }
}
