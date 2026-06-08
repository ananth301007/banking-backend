package com.example.banking_backend.service;

import com.example.banking_backend.model.Transaction;
import com.example.banking_backend.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    @Transactional
    public Transaction deposit(Long accountId, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        Transaction transaction = new Transaction(null, accountId, amount, "DEPOSIT");
        return transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public Transaction withdraw(Long accountId, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        Transaction transaction = new Transaction(accountId, null, amount, "WITHDRAWAL");
        return transactionRepository.save(transaction);
    }

    @Override
    @Transactional
    public Transaction transfer(Long sourceAccountId, Long targetAccountId, BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        if (sourceAccountId.equals(targetAccountId)) {
            throw new IllegalArgumentException("Source and target accounts cannot be the same");
        }
        Transaction transaction = new Transaction(sourceAccountId, targetAccountId, amount, "TRANSFER");
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionHistory(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }
}