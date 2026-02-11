package com.digitalwallet.DTO.Wallet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


public class GetWalletsResponse {
    public List<GetWalletResponse> list;
}
