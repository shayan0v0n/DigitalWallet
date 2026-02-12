package com.digitalwallet.DTO.Wallet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActiveWalletResponse {
    public UUID walletId;
}
