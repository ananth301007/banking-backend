package com.example.banking_backend.controller;

import com.example.banking_backend.dto.AccountDto;
import com.example.banking_backend.model.Account;
import com.example.banking_backend.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@Valid @RequestBody AccountDto accountDto) {
        Account response = accountService.createAccount(accountDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Account> getAccountByNumber(@PathVariable String accountNumber) {
        Account response = accountService.getAccountByNumber(accountNumber);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{accountNumber}/block")
    public ResponseEntity<Account> blockAccount(@PathVariable String accountNumber) {
        Account response = accountService.blockAccount(accountNumber);
        return ResponseEntity.ok(response);
    }
}