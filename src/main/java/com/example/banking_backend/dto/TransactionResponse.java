package com.example.banking_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TransactionResponse {
    private String message;
    private String transactionId;
    private BigDecimal currentBalance;
    private LocalDateTime timestamp;
}