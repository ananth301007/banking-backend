package com.example.banking_backend.service;

import com.example.banking_backend.dto.*;
import com.example.banking_backend.entity.Transaction;
import com.example.banking_backend.entity.Transfer;
import com.example.banking_backend.exception.InsufficientBalanceException;
import com.example.banking_backend.repository.TransactionRepository;
import com.example.banking_backend.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransferRepository transferRepository;

    @Override
    @Transactional
    public TransactionResponse deposit(DepositRequest request) {
        // Create and save transaction entry
        Transaction transaction = Transaction.builder()
                .accountId(request.getAccountId())
                .amount(request.getAmount())
                .transactionType("DEPOSIT")
                .build();

        transactionRepository.save(transaction);

        return TransactionResponse.builder()
                .message("Amount deposited successfully!")
                .transactionId(UUID.randomUUID().toString())
                .currentBalance(request.getAmount()) // Dummy balance response for now
                .timestamp(transaction.getTimestamp())
                .build();
    }

    @Override
    @Transactional
    public TransactionResponse withdraw(WithdrawRequest request) {
        // Business Logic Check: Simple mock balance check for demo
        BigDecimal standardLimit = new BigDecimal("10000");
        if (request.getAmount().compareTo(standardLimit) > 0) {
            throw new InsufficientBalanceException("Insufficient funds in account: " + request.getAccountId());
        }

        Transaction transaction = Transaction.builder()
                .accountId(request.getAccountId())
                .amount(request.getAmount())
                .transactionType("WITHDRAW")
                .build();

        transactionRepository.save(transaction);

        return TransactionResponse.builder()
                .message("Amount withdrawn successfully!")
                .transactionId(UUID.randomUUID().toString())
                .currentBalance(standardLimit.subtract(request.getAmount()))
                .timestamp(transaction.getTimestamp())
                .build();
    }

    @Override
    @Transactional
    public TransactionResponse transferFunds(TransferRequest request) {
        // Save into transfers table
        Transfer transfer = Transfer.builder()
                .fromAccountId(request.getFromAccountId())
                .toAccountId(request.getToAccountId())
                .amount(request.getAmount())
                .status("SUCCESS")
                .build();

        transferRepository.save(transfer);

        // Also create a debit transaction entry for Sender
        Transaction debitTx = Transaction.builder()
                .accountId(request.getFromAccountId())
                .amount(request.getAmount())
                .transactionType("TRANSFER_DEBIT")
                .build();
        transactionRepository.save(debitTx);

        return TransactionResponse.builder()
                .message("Fund transferred successfully to " + request.getToAccountId())
                .transactionId(UUID.randomUUID().toString())
                .currentBalance(BigDecimal.ZERO)
                .timestamp(transfer.getTimestamp())
                .build();
    }

    @Override
    public List<Transaction> getTransactionHistory(String accountId) {
        return transactionRepository.findByAccountIdOrderByTimestampDesc(accountId);
    }
}
