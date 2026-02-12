package com.digitalwallet.DTO.Currency;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRatioCurrencyRequest {
    public double ratio;
}
