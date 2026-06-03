package com.example.banking_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Account number is mandatory")
    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;

    @NotBlank(message = "Account type (SAVINGS/CURRENT) is mandatory")
    @Column(name = "account_type", nullable = false)
    private String accountType;

    @NotNull(message = "Initial balance is mandatory")
    @Column(nullable = false)
    private BigDecimal balance;

    @NotBlank(message = "Status is mandatory")
    @Column(nullable = false)
    private String status = "ACTIVE"; // ACTIVE, BLOCKED

    @NotNull(message = "Customer mapping link ID is required")
    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    public Account() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }
    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }
    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }
}