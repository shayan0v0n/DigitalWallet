package com.digitalwallet.DTO.Wallet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class GetWalletResponse {
    public UUID  walletId;
    public String  walletName;
    public String  walletAddress;
    public boolean  isPublic;
}
