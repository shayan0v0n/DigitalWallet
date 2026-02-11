package com.digitalwallet.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name="wallets")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wallet extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private boolean isPublic;
}
