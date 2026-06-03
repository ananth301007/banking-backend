package com.example.banking_backend.service;

import com.example.banking_backend.dto.*;
import com.example.banking_backend.entity.Transaction;
import java.util.List;

public interface TransactionService {

    TransactionResponse deposit(DepositRequest request);

    TransactionResponse withdraw(WithdrawRequest request);

    TransactionResponse transferFunds(TransferRequest request);

    List<Transaction> getTransactionHistory(String accountId);
}