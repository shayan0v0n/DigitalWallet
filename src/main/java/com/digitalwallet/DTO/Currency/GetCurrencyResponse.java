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
public class GetCurrencyResponse {
    public UUID id;
    public double ratio;
    public String name;
    public String code;
}
