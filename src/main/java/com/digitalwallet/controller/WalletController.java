package com.digitalwallet.controller;

import com.digitalwallet.DTO.Wallet.CreateWalletRequest;
import com.digitalwallet.DTO.Wallet.CreateWalletResponse;
import com.digitalwallet.DTO.Wallet.GetWalletResponse;
import com.digitalwallet.DTO.Wallet.GetWalletsResponse;
import com.digitalwallet.entity.Wallet;
import com.digitalwallet.service.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/wallets")
@RequiredArgsConstructor
@Tag(name = "Wallet Management", description = "Wallet Management APIs")
public class WalletController {
    private final WalletService walletService;

    @GetMapping()
    @Operation(summary = "get all wallets")
    public ResponseEntity<GetWalletsResponse> getWallets() {
        GetWalletsResponse response = walletService.getWallets();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{walletId}")
    @Operation(summary = "Get wallet by wallet id")
    public ResponseEntity<GetWalletResponse> getWallet(
            @PathVariable UUID walletId
    ) {
        GetWalletResponse response = walletService.getWallet(walletId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    @Operation(summary = "Create wallet")
    public ResponseEntity<CreateWalletResponse> createWallet(
            @Valid @RequestBody CreateWalletRequest dto
            ) {
        CreateWalletResponse response = walletService.createWallet(dto);
        return ResponseEntity.ok(response);
    }
}