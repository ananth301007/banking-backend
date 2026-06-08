package com.example.banking_backend.service;

import com.example.banking_backend.model.Loan;
import java.util.List;

public interface LoanService {
    Loan applyForLoan(Loan loan);
    Loan updateLoanStatus(Long loanId, String status);
    List<Loan> getLoansByCustomerId(Long customerId);
}