package com.digitalwallet.service;

import com.digitalwallet.DTO.Wallet.CreateWalletRequest;
import com.digitalwallet.DTO.Wallet.CreateWalletResponse;
import com.digitalwallet.DTO.Wallet.GetWalletResponse;
import com.digitalwallet.DTO.Wallet.GetWalletsResponse;
import com.digitalwallet.entity.Wallet;
import com.digitalwallet.repository.WalletRepository;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;

//    public GetWalletResponse getWallet(UUID walletId) {
//        Wallet getWallet = walletRepository.findById(walletId)
//                .orElseThrow(() -> new RuntimeException("Wallet not found"));
//
//        return GetWalletResponse.builder()
//                .walletId(getWallet.getId())
//                .walletName(getWallet.getName())
//                .walletAddress(getWallet.getAddress())
//                .isPublic(getWallet.isPublic())
//                .build();
//    }
//
//    public GetWalletsResponse getWallets() {
//        List<Wallet> wallets = walletRepository.findAll();
//        List<GetWalletResponse> walletList = wallets.stream()
//                .map(wallet -> GetWalletResponse.builder()
//                        .walletId(wallet.getId())
//                        .walletName(wallet.getName())
//                        .isPublic(wallet.isPublic())
//                        .build()
//                )
//                .toList();
//
//        return GetWalletsResponse.builder()
//                .list(walletList)
//                .build();
//    }
//
//    public CreateWalletResponse createWallet(CreateWalletRequest dto) {
//        Wallet walletPayload = Wallet.builder()
//                .name(dto.getWalletName())
//                .address(dto.getWalletAddress())
//                .isPublic(dto.isPublicWallet())
//                .build();
//
//        Wallet walletCreated = walletRepository.save(walletPayload);
//
//        return CreateWalletResponse.builder()
//                .walletId(walletCreated.getId())
//                .build();
//    }
}
