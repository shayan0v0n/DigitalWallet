package com.digitalwallet.DTO.Transaction;

import com.digitalwallet.constant.TransactionKind;
import com.digitalwallet.constant.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetTransactionResponse {
    public UUID transactionId;
    public String description;
    public double amount;
    public TransactionKind kind;      // e.g., CREDIT, DEBIT
    public TransactionType type;      // e.g., DEPOSIT, WITHDRAWAL, TRANSFER
    public Instant createdAt;  // from BaseEntity
    public Instant updatedAt;  // optional, from BaseEntity
}
