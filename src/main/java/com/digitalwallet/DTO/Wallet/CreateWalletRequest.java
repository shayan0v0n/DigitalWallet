package com.digitalwallet.DTO.Wallet;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CreateWalletRequest {
    private String title;
    private UUID userId;
    private UUID currencyId;
}
