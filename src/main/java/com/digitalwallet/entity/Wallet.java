package com.digitalwallet.entity;

import com.digitalwallet.constant.WalletStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name="wallets")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class Wallet extends BaseEntity {
    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private double balance;

    @Column(nullable = false)
    private WalletStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_id", nullable = false)
    private Currency currency;

    @OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;
}
