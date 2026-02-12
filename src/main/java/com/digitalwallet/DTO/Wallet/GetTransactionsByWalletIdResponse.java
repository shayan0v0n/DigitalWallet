package com.digitalwallet.DTO.Wallet;

import com.digitalwallet.DTO.Transaction.GetTransactionResponse;
import com.digitalwallet.constant.TransactionKind;
import com.digitalwallet.constant.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
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
