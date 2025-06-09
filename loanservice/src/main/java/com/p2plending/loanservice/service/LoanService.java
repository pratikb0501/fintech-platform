package com.p2plending.loanservice.service;

import com.p2plending.loanservice.dto.LoanRequest;
import com.p2plending.loanservice.model.Loan;
import com.p2plending.loanservice.model.LoanStatus;

import java.util.List;
import java.util.UUID;

public interface LoanService {

    Loan createLoan(LoanRequest loan);

    Loan getLoanById(UUID loanId);

    List<Loan> getLoansByUserId(UUID userId);

    Loan approveLoan(UUID loanId);

    Loan rejectLoan(UUID loanId);

    Loan payLoan(UUID loanId);
}
