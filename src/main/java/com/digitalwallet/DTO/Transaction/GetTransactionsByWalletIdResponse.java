package com.digitalwallet.DTO.Transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetTransactionsByWalletIdResponse {
    public UUID walletId;
    public List<GetTransactionResponse> transactions;
    public int totalCount;
}
