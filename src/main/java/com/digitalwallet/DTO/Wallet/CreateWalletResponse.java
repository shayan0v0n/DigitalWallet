package com.digitalwallet.DTO.Wallet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;

import java.util.UUID;

@Setter
@Builder
@AllArgsConstructor
public class CreateWalletResponse {
    public UUID walletId;
}
