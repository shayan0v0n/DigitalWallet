package com.digitalwallet.service;

import com.digitalwallet.DTO.Currency.*;
import com.digitalwallet.entity.Currency;
import com.digitalwallet.entity.Wallet;
import com.digitalwallet.exception.ResourceNotFoundException;
import com.digitalwallet.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CurrencyService {
    private final CurrencyRepository currencyRepository;

    public GetCurrenciesResponse getAllCurrencies() {
        List<Currency>  currencies = currencyRepository.findAll();
        List<GetCurrencyResponse> currenciesList = currencies.stream()
                .map(currency -> GetCurrencyResponse.builder()
                        .id(currency.getId())
                        .name(currency.getName())
                        .code(currency.getCode())
                        .ratio(currency.getRatio())
                        .build()
                ).toList();

        return GetCurrenciesResponse.builder()
                .currencies(currenciesList)
                .build();
    }

    public CreateCurrencyResponse  createCurrency(CreateCurrencyRequest request) {
        Currency currency = Currency.builder()
                .name(request.name)
                .ratio(request.ratio)
                .code(request.code)
                .build();
        currencyRepository.save(currency);

        return CreateCurrencyResponse.builder()
                .currencyId(currency.getId())
                .build();
    }

    public UpdateRatioCurrencyResponse updateRatioCurrency(UUID currencyId, UpdateRatioCurrencyRequest request) {
        Currency currency = currencyRepository.findById(currencyId)
                .orElseThrow(() -> new ResourceNotFoundException("Currency not found with id: " + currencyId));

        currency.setRatio(request.ratio);
        currencyRepository.save(currency);

        return UpdateRatioCurrencyResponse.builder()
                .currencyId(currency.getId())
                .build();
    }
}