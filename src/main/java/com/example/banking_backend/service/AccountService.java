package com.example.banking_backend.service;

import com.example.banking_backend.dto.AccountDto;
import com.example.banking_backend.model.Account;

public interface AccountService {
    Account createAccount(AccountDto accountDto);
    Account getAccountByNumber(String accountNumber);
    Account blockAccount(String accountNumber);
}