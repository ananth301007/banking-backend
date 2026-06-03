package com.example.banking_backend.controller;

import com.example.banking_backend.dto.*;
import com.example.banking_backend.entity.Transaction;
import com.example.banking_backend.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    // 1. DEPOSIT API
    @PostMapping("/deposit")
    public ResponseEntity<TransactionResponse> deposit(@RequestBody DepositRequest request) {
        return ResponseEntity.ok(transactionService.deposit(request));
    }

    // 2. WITHDRAW API
    @PostMapping("/withdraw")
    public ResponseEntity<TransactionResponse> withdraw(@RequestBody WithdrawRequest request) {
        return ResponseEntity.ok(transactionService.withdraw(request));
    }

    // 3. FUND TRANSFER API
    @PostMapping("/transfer")
    public ResponseEntity<TransactionResponse> transfer(@RequestBody TransferRequest request) {
        return ResponseEntity.ok(transactionService.transferFunds(request));
    }

    // 4. GET TRANSACTION HISTORY / STATEMENTS API
    @GetMapping("/history/{accountId}")
    public ResponseEntity<List<Transaction>> getHistory(@PathVariable String accountId) {
        return ResponseEntity.ok(transactionService.getTransactionHistory(accountId));
    }
}
