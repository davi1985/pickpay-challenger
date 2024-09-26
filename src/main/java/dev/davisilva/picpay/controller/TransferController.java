package dev.davisilva.picpay.controller;


import dev.davisilva.picpay.controller.dto.TransferDTO;
import dev.davisilva.picpay.entity.Transfer;
import dev.davisilva.picpay.service.TransferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @PostMapping("/transfer")
    public ResponseEntity<Transfer> transfer(@RequestBody @Valid TransferDTO dto) {
        var response = transferService.transfer(dto);

        return ResponseEntity.ok(response);
    }
}
