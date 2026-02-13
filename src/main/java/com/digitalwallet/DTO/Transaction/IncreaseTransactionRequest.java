package com.digitalwallet.DTO.Transaction;

import lombok.*;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncreaseTransactionRequest {
    public double amount;
    public String destination;
}
