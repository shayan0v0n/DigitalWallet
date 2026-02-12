package com.digitalwallet.DTO.Currency;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.util.List;

@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetCurrenciesResponse {
    public List<GetCurrencyResponse> currencies;
}
