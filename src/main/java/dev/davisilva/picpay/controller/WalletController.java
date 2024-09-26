package dev.davisilva.picpay.controller;

import dev.davisilva.picpay.controller.dto.CreateWalletDto;
import dev.davisilva.picpay.entity.Wallet;
import dev.davisilva.picpay.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WalletController {

    private final WalletService walletService;

    @PostMapping("/wallets")
    public ResponseEntity<Wallet> create(@RequestBody @Valid CreateWalletDto dto) {
        var wallet = walletService.create(dto);

        return ResponseEntity.ok(wallet);
    }
}










