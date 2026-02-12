package com.digitalwallet.controller;

import com.digitalwallet.DTO.Wallet.*;
import com.digitalwallet.service.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/wallets")
@RequiredArgsConstructor
@Tag(name = "Wallet Management", description = "Wallet Management APIs")
public class WalletController {
    private final WalletService walletService;

    @GetMapping
    @Operation(summary = "get all wallets")
    public ResponseEntity<GetWalletsResponse> getWallets() {
        GetWalletsResponse response = walletService.getWallets();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{walletId}")
    @Operation(summary = "Get wallet by wallet id")
    public ResponseEntity<GetWalletResponse> getWallet(@PathVariable UUID walletId) {
        GetWalletResponse response = walletService.getWallet(walletId);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Operation(summary = "Create wallet")
    public ResponseEntity<CreateWalletResponse> createWallet(
            @Valid @RequestBody CreateWalletRequest request
    ) {
        CreateWalletResponse response = walletService.createWallet(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{walletId}/transactions")
    @Operation(summary = "get all transactions by wallet id")
    public ResponseEntity<GetTransactionsByWalletIdResponse> getTransactionsByWalletId(@PathVariable UUID walletId) {
        GetTransactionsByWalletIdResponse response = walletService.getTransactionsByWalletId(walletId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{walletId}/active")
    @Operation(summary = "activate wallet by wallet id")
    public ResponseEntity<ActiveWalletResponse> activateWallet(@PathVariable UUID walletId) {
        ActiveWalletResponse response = walletService.activateWallet(walletId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{walletId}")
    @Operation(summary = "update wallet by wallet id")
    public ResponseEntity<UpdateWalletResponse> updateWallet(
            @PathVariable UUID walletId,
            @Valid @RequestBody UpdateWalletRequest request
    ) {
        UpdateWalletResponse response = walletService.updateWallet(walletId, request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{walletId}/suspend")
    @Operation(summary = "suspend wallet by wallet id")
    public ResponseEntity<SuspendWalletResponse> suspendWallet(@PathVariable UUID walletId) {
        SuspendWalletResponse response = walletService.suspendWallet(walletId);
        return ResponseEntity.ok(response);
    }
}