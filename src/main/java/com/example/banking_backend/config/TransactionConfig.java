package com.example.banking_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.logging.Logger;

@Configuration
public class TransactionConfig {

    @Bean
    public Logger transactionLogger() {
        return Logger.getLogger("TransactionModuleLogger");
    }
}