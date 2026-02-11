package com.digitalwallet;

import com.digitalwallet.entity.Wallet;
import com.digitalwallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
@RequiredArgsConstructor
public class DigitalWalletApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalWalletApplication.class, args);
    }
}