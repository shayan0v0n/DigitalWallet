package com.digitalwallet.DTO.Wallet;

import lombok.*;

import java.util.List;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetWalletsResponse {
    public List<GetWalletResponse> wallets;
}
