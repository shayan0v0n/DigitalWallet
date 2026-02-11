package com.digitalwallet.controller;

import com.digitalwallet.DTO.Currency.*;
import com.digitalwallet.service.CurrencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/currencies")
@Tag(name = "Currency Management", description = "Currency API")
public class CurrencyController {
    private final CurrencyService currencyService;

    @PatchMapping("/{currencyId}")
    @Operation(summary = "update ratio by currency id")
    public ResponseEntity<UpdateRatioCurrencyResponse> updateRatioCurrency(@PathVariable UUID currencyId, @RequestBody UpdateRatioCurrencyRequest request) {
        UpdateRatioCurrencyResponse response = new UpdateRatioCurrencyResponse();
        return  ResponseEntity.ok(response);
    }

    @PostMapping()
    @Operation(summary = "add currency")
    public ResponseEntity<CreateCurrencyResponse>  createCurrency(@RequestBody CreateCurrencyRequest request) {
        CreateCurrencyResponse response = new CreateCurrencyResponse();
        return  ResponseEntity.ok(response);
    }

    @GetMapping()
    @Operation(summary = "get all currencies")
    public ResponseEntity<GetCurrenciesResponse> getCurrencies() {
        GetCurrenciesResponse response = new GetCurrenciesResponse();
        return  ResponseEntity.ok(response);
    }
}
