package com.digitalwallet.DTO.Transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DecreaseTransactionRequest {
    public double amount;
    public String destination;
}
