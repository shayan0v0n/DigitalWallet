package com.digitalwallet.controller;

import com.digitalwallet.DTO.Transaction.*;
import com.digitalwallet.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Transaction Management", description = "Transaction Management API")
@RequestMapping("api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("/{walletId}")
    @Operation(summary = "get all transactions by wallet id")
    public ResponseEntity<GetTransactionsByWalletIdResponse> getTransactionsByWalletId(@PathVariable("walletId") String walletId) {
        GetTransactionsByWalletIdResponse response = new GetTransactionsByWalletIdResponse();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{walletId}/increase")
    @Operation(summary = "add incremental transaction to wallet")
    public ResponseEntity<IncreaseTransactionResponse>  increaseTransaction(@PathVariable("walletId") String walletId, @RequestBody IncreaseTransactionRequest request) {
        IncreaseTransactionResponse response = new IncreaseTransactionResponse();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{walletId}/decrease")
    @Operation(summary = "add decremental transaction to wallet")
    public ResponseEntity<DecreaseTransactionResponse>  decreaseTransaction(@PathVariable("walletId") String walletId, @RequestBody DecreaseTransactionRequest request) {
        DecreaseTransactionResponse response = new DecreaseTransactionResponse();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/funds")
    @Operation(summary = "fund transaction between 2 wallets")
    public ResponseEntity<TransactionFundsResponse> transactionFunds(@RequestBody TransactionFundsRequest request)  {
        TransactionFundsResponse response = new TransactionFundsResponse();
        return ResponseEntity.ok(response);
    }
}
