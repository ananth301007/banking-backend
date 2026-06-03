package com.example.banking_backend.service;

import com.example.banking_backend.dto.AccountDto;
import com.example.banking_backend.model.Account;
import com.example.banking_backend.repository.AccountRepository;
import com.example.banking_backend.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional
    public Account createAccount(AccountDto dto) {
        Account account = new Account();
        // Automatically generate a unique random 10 digit banking account number map
        String generatedAccNum = String.valueOf((long) (Math.random() * 9000000000L) + 1000000000L);

        account.setAccountNumber(generatedAccNum);
        account.setAccountType(dto.getAccountType().toUpperCase());
        account.setBalance(dto.getBalance());
        account.setCustomerId(dto.getCustomerId());
        account.setStatus("ACTIVE");

        return accountRepository.save(account);
    }

    @Override
    @Transactional(readOnly = true)
    public Account getAccountByNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found for reference number: " + accountNumber));
    }

    @Override
    @Transactional
    public Account blockAccount(String accountNumber) {
        Account account = getAccountByNumber(accountNumber);
        account.setStatus("BLOCKED");
        return accountRepository.save(account);
    }
}