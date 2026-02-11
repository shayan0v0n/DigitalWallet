package com.digitalwallet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Currencies")
public class Currency extends BaseEntity {
    @Column
    public String code;

    @Column
    public String name;

    @Column
    public double ratio;

    @OneToMany(mappedBy = "currency", cascade = CascadeType.ALL)
    private List<Wallet> wallets;
}
