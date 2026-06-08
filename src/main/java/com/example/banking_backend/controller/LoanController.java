package com.example.banking_backend.controller;

import com.example.banking_backend.model.Loan;
import com.example.banking_backend.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/apply")
    public ResponseEntity<Loan> applyLoan(@RequestBody Loan loan) {
        Loan newLoan = loanService.applyForLoan(loan);
        return new ResponseEntity<>(newLoan, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Loan> updateStatus(@PathVariable Long id, @RequestBody Map<String, String> request) {
        String status = request.get("status");
        Loan updatedLoan = loanService.updateLoanStatus(id, status);
        return ResponseEntity.ok(updatedLoan);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Loan>> getCustomerLoans(@PathVariable Long customerId) {
        List<Loan> loans = loanService.getLoansByCustomerId(customerId);
        return ResponseEntity.ok(loans);
    }
}