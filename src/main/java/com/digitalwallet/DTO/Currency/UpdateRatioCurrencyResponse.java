package com.digitalwallet.DTO.Currency;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRatioCurrencyResponse {
        public UUID currencyId;
}
