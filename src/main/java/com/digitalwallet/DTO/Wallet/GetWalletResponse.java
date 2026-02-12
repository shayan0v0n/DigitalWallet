package com.digitalwallet.DTO.Wallet;

import com.digitalwallet.constant.WalletStatus;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetWalletResponse {
    public UUID  walletId;
    public String  title;
    public double  balance;
    public UUID currencyId;
    public String  currencyName;
    public WalletStatus  walletStatus;
    public UUID userId;
    public Instant createdAt;
    public Instant updatedAt;
}
