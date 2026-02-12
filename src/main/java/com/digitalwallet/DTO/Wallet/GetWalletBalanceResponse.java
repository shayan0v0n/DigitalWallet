package com.digitalwallet.DTO.Wallet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetWalletBalanceResponse {
    public double balance;
}
