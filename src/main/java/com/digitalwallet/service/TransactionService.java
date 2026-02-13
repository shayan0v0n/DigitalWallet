package com.digitalwallet.service;

import com.digitalwallet.DTO.Transaction.*;
import com.digitalwallet.constant.TransactionKind;
import com.digitalwallet.constant.TransactionType;
import com.digitalwallet.constant.WalletStatus;
import com.digitalwallet.entity.Transaction;
import com.digitalwallet.entity.Wallet;
import com.digitalwallet.exception.InsufficientFundsException;
import com.digitalwallet.exception.ResourceNotFoundException;
import com.digitalwallet.repository.TransactionRepository;
import com.digitalwallet.repository.WalletRepository;
import com.digitalwallet.utils.UuidParsingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final WalletService walletService;

    @Transactional(readOnly = true)
    public GetTransactionsByWalletIdResponse getTransactionsByWalletId(String walletIdStr) {
        UUID walletId = UuidParsingUtil.parseUUID(walletIdStr, "Invalid wallet ID format");
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found with id: " + walletId));

        List<GetTransactionResponse> transactionDTOs = wallet.getTransactions().stream()
                .map(tx -> GetTransactionResponse.builder()
                        .transactionId(tx.getId())
                        .description(tx.getDescription())
                        .amount(tx.getAmount())
                        .kind(tx.getKind())
                        .type(tx.getType())
                        .createdAt(tx.getCreatedAt())
                        .build())
                .collect(Collectors.toList());

        return GetTransactionsByWalletIdResponse.builder()
                .walletId(wallet.getId())
                .transactions(transactionDTOs)
                .totalCount(transactionDTOs.size())
                .build();
    }

    @Transactional
    public IncreaseTransactionResponse increaseTransaction(String walletIdStr, IncreaseTransactionRequest request) {
        UUID walletId = UuidParsingUtil.parseUUID(walletIdStr, "Invalid wallet ID format");
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found with id: " + walletId));

        walletService.validateWalletActive(wallet);

        // Update wallet balance
        double newBalance = wallet.getBalance() + request.getAmount();
        wallet.setBalance(newBalance);
        walletRepository.save(wallet);

        // Create transaction record
        Transaction transaction = Transaction.builder()
                .description(request.getDestination())
                .amount(request.getAmount())
                .kind(TransactionKind.Incremental)
                .type(TransactionType.User)
                .wallet(wallet)
                .build();

        Transaction savedTransaction = transactionRepository.save(transaction);

        return IncreaseTransactionResponse.builder()
                .transactionId(savedTransaction.getId())
                .transactionId(wallet.getId())
                .build();
    }

    @Transactional
    public DecreaseTransactionResponse decreaseTransaction(String walletIdStr, DecreaseTransactionRequest request) {
        UUID walletId = UuidParsingUtil.parseUUID(walletIdStr, "Invalid wallet ID format");
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found with id: " + walletId));

        walletService.validateWalletActive(wallet);

        // Check sufficient balance
        if (wallet.getBalance() < request.getAmount()) {
            throw new InsufficientFundsException("Insufficient balance. Available: " + wallet.getBalance());
        }

        // Update wallet balance
        double newBalance = wallet.getBalance() - request.getAmount();
        wallet.setBalance(newBalance);
        walletRepository.save(wallet);

        // Create transaction record
        Transaction transaction = Transaction.builder()
                .description(request.getDestination())
                .amount(request.getAmount())
                .kind(TransactionKind.Decremental)
                .type(TransactionType.User)
                .wallet(wallet)
                .build();

        Transaction savedTransaction = transactionRepository.save(transaction);

        return DecreaseTransactionResponse.builder()
                .transactionId(savedTransaction.getId())
                .build();
    }

    @Transactional
    public TransactionFundsResponse transactionFunds(TransactionFundsRequest request) {
        UUID fromWalletId = UuidParsingUtil.parseUUID(request.getFromWalletId(), "Invalid source wallet ID format");
        UUID toWalletId = UuidParsingUtil.parseUUID(request.getToWalletId(), "Invalid destination wallet ID format");

        Wallet sourceWallet = walletRepository.findById(fromWalletId)
                .orElseThrow(() -> new ResourceNotFoundException("Source wallet not found with id: " + fromWalletId));
        Wallet targetWallet = walletRepository.findById(toWalletId)
                .orElseThrow(() -> new ResourceNotFoundException("Destination wallet not found with id: " + toWalletId));

        walletService.validateWalletActive(sourceWallet);
        walletService.validateWalletActive(targetWallet);

        if (!sourceWallet.getCurrency().getId().equals(targetWallet.getCurrency().getId())) {
            throw new IllegalArgumentException("Currency mismatch between wallets");
        }

        if (sourceWallet.getBalance() < request.getAmount()) {
            throw new InsufficientFundsException("Insufficient balance in source wallet. Available: " + sourceWallet.getBalance());
        }

        sourceWallet.setBalance(sourceWallet.getBalance() - request.getAmount());
        targetWallet.setBalance(targetWallet.getBalance() + request.getAmount());

        walletRepository.save(sourceWallet);
        walletRepository.save(targetWallet);

        // Create debit transaction for source wallet
        Transaction debitTx = Transaction.builder()
                .description(request.getDescription() != null ? request.getDescription() : "Fund transfer to wallet " + targetWallet.getId())
                .amount(request.getAmount())
                .kind(TransactionKind.Decremental)
                .type(TransactionType.Funds)
                .wallet(sourceWallet)
                .build();

        // Create credit transaction for target wallet
        Transaction creditTx = Transaction.builder()
                .description(request.getDescription() != null ? request.getDescription() : "Fund transfer from wallet " + sourceWallet.getId())
                .amount(request.getAmount())
                .kind(TransactionKind.Incremental)
                .type(TransactionType.Funds)
                .wallet(targetWallet)
                .build();

        transactionRepository.save(debitTx);
        transactionRepository.save(creditTx);

        return TransactionFundsResponse.builder()
                .fromTransactionId(debitTx.getId())
                .toTransactionId(creditTx.getId())
                .build();
    }
}