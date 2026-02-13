package com.digitalwallet.DTO.Transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionFundsResponse {
    public UUID fromTransactionId;
    public UUID toTransactionId;
}
