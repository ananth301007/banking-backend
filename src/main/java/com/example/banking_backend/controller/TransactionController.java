package com.example.banking_backend.controller;

import com.example.banking_backend.model.Transaction;
import com.example.banking_backend.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/deposit")
    public ResponseEntity<Transaction> deposit(@RequestBody Map<String, Object> request) {
        Long accountId = Long.valueOf(request.get("accountId").toString());
        BigDecimal amount = new BigDecimal(request.get("amount").toString());
        Transaction tx = transactionService.deposit(accountId, amount);
        return new ResponseEntity<>(tx, HttpStatus.CREATED);
    }

    @PostMapping("/withdraw")
    public ResponseEntity<Transaction> withdraw(@RequestBody Map<String, Object> request) {
        Long accountId = Long.valueOf(request.get("accountId").toString());
        BigDecimal amount = new BigDecimal(request.get("amount").toString());
        Transaction tx = transactionService.withdraw(accountId, amount);
        return new ResponseEntity<>(tx, HttpStatus.CREATED);
    }

    @PostMapping("/transfer")
    public ResponseEntity<Transaction> transfer(@RequestBody Map<String, Object> request) {
        Long sourceAccountId = Long.valueOf(request.get("sourceAccountId").toString());
        Long targetAccountId = Long.valueOf(request.get("targetAccountId").toString());
        BigDecimal amount = new BigDecimal(request.get("amount").toString());
        Transaction tx = transactionService.transfer(sourceAccountId, targetAccountId, amount);
        return new ResponseEntity<>(tx, HttpStatus.CREATED);
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<Transaction>> getHistory(@PathVariable Long accountId) {
        List<Transaction> history = transactionService.getTransactionHistory(accountId);
        return ResponseEntity.ok(history);
    }
}