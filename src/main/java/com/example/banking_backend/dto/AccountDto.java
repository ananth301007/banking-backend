package com.example.banking_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class AccountDto {
    @NotBlank(message = "Account type is required")
    private String accountType;

    @NotNull(message = "Initial opening deposit is required")
    private BigDecimal balance;

    @NotNull(message = "Associated Customer identity reference mapping ID is required")
    private Long customerId;

    // Getters and Setters
    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }
    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }
}
