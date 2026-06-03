package com.example.banking_backend.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class TransferRequest {
    private String fromAccountId;
    private String toAccountId;
    private BigDecimal amount;
}