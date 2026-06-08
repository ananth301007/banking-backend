package com.example.banking_backend.service;

import com.example.banking_backend.model.Transaction;
import java.util.List;

public interface TransactionService {
    Transaction deposit(Long accountId, java.math.BigDecimal amount);
    Transaction withdraw(Long accountId, java.math.BigDecimal amount);
    Transaction transfer(Long sourceAccountId, Long targetAccountId, java.math.BigDecimal amount);
    List<Transaction> getTransactionHistory(Long accountId);
}