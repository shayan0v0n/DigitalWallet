package com.digitalwallet.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Table(name = "Currencies")
public class Currency extends BaseEntity {
    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double ratio;

    @OneToMany(mappedBy = "currency", cascade = CascadeType.ALL)
    private List<Wallet> wallets;
}
