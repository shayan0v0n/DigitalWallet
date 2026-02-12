package com.digitalwallet.service;

import com.digitalwallet.DTO.Transaction.GetTransactionResponse;
import com.digitalwallet.DTO.Wallet.*;
import com.digitalwallet.constant.WalletStatus;
import com.digitalwallet.entity.Currency;
import com.digitalwallet.entity.Wallet;
import com.digitalwallet.exception.ResourceNotFoundException;
import com.digitalwallet.repository.CurrencyRepository;
import com.digitalwallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;
    private final CurrencyRepository currencyRepository;

    @Transactional
    public CreateWalletResponse createWallet(CreateWalletRequest request) {
        Currency currency = currencyRepository.findById(request.getCurrencyId())
                .orElseThrow(() -> new ResourceNotFoundException("Currency not found with id: " + request.getCurrencyId()));

        Wallet wallet = Wallet.builder()
                .title(request.getTitle())
                .balance(0.0)
                .status(WalletStatus.Active)
                .userId(request.getUserId())
                .currency(currency)
                .build();

        Wallet savedWallet = walletRepository.save(wallet);

        return CreateWalletResponse.builder()
                .walletId(savedWallet.getId())
                .build();
    }

    @Transactional(readOnly = true)
    public GetWalletsResponse getWallets() {
        List<Wallet> wallets = walletRepository.findAll();

        List<GetWalletResponse> walletResponses = wallets.stream()
                .map(wallet ->
                        GetWalletResponse.builder()
                                .walletId(wallet.getId())
                                .title(wallet.getTitle())
                                .balance(wallet.getBalance())
                                .walletStatus(wallet.getStatus())
                                .userId(wallet.getUserId())
                                .currencyId(wallet.getCurrency().getId())
                                .currencyName(wallet.getCurrency().getName())
                                .createdAt(wallet.getCreatedAt())
                                .updatedAt(wallet.getUpdatedAt())
                                .build())
                .collect(Collectors.toList());

        return GetWalletsResponse.builder()
                .wallets(walletResponses)
                .build();
    }

    @Transactional(readOnly = true)
    public GetWalletResponse getWallet(UUID walletId) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found with id: " + walletId));

        return GetWalletResponse.builder()
                .walletId(wallet.getId())
                .title(wallet.getTitle())
                .balance(wallet.getBalance())
                .walletStatus(wallet.getStatus())
                .userId(wallet.getUserId())
                .currencyId(wallet.getCurrency().getId())
                .currencyName(wallet.getCurrency().getName())
                .createdAt(wallet.getCreatedAt())
                .updatedAt(wallet.getUpdatedAt())
                .build();
    }

    @Transactional(readOnly = true)
    public GetTransactionsByWalletIdResponse getTransactionsByWalletId(UUID walletId) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found with id: " + walletId));

        List<GetTransactionResponse> transactionDTOs =
                wallet.getTransactions().stream()
                        .map(transaction -> GetTransactionResponse.builder()
                                .transactionId(transaction.getId())
                                .description(transaction.getDescription())
                                .amount(transaction.getAmount())
                                .kind(transaction.getKind())           // use kind, not status
                                .type(transaction.getType())
                                .createdAt(transaction.getCreatedAt())
                                .updatedAt(transaction.getUpdatedAt()) // if needed
                                .build())
                        .collect(Collectors.toList());

        return GetTransactionsByWalletIdResponse.builder()
                .walletId(wallet.getId())
                .transactions(transactionDTOs)
                .totalCount(transactionDTOs.size())
                .build();
    }

    @Transactional
    public ActiveWalletResponse activateWallet(UUID walletId) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found with id: " + walletId));

        if (wallet.getStatus() == WalletStatus.Active) {
            throw new IllegalStateException("Wallet is already active");
        }

        wallet.setStatus(WalletStatus.Active);
        Wallet updatedWallet = walletRepository.save(wallet);

        return ActiveWalletResponse.builder()
                .walletId(updatedWallet.getId())
                .build();
    }

    @Transactional
    public SuspendWalletResponse suspendWallet(UUID walletId) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found with id: " + walletId));

        if (wallet.getStatus() == WalletStatus.Suspend) {
            throw new IllegalStateException("Wallet is already suspended");
        }

        wallet.setStatus(WalletStatus.Suspend);
        Wallet updatedWallet = walletRepository.save(wallet);

        return SuspendWalletResponse.builder()
                .walletId(updatedWallet.getId())
                .build();
    }

    @Transactional
    public UpdateWalletResponse updateWallet(UUID walletId, UpdateWalletRequest request) {
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found with id: " + walletId));

        if (request.getTitle() != null && !request.getTitle().isEmpty()) {
            wallet.setTitle(request.getTitle());
        }

        Wallet updatedWallet = walletRepository.save(wallet);

        return UpdateWalletResponse.builder()
                .walletId(updatedWallet.getId())
                .build();
    }

}