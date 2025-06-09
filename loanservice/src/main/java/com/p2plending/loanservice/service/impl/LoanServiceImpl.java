package com.p2plending.loanservice.service.impl;

import com.p2plending.loanservice.dto.LoanRequest;
import com.p2plending.loanservice.model.Loan;
import com.p2plending.loanservice.model.LoanStatus;
import com.p2plending.loanservice.repository.LoanRepository;
import com.p2plending.loanservice.service.LoanService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

    @Override
    public Loan createLoan(LoanRequest request) {
        Loan loan = Loan.builder()
                .userId(request.getUserId())
                .amount(request.getAmount())
                .tenure(request.getTenure())
                .interestRate(request.getInterestRate())
                .build();

        return loanRepository.save(loan);
    }

    @Override
    public Loan getLoanById(UUID loanId) {
        return loanRepository.findById(loanId).orElseThrow(()->new EntityNotFoundException("Loan with id " + loanId + " not found"));
    }

    @Override
    public List<Loan> getLoansByUserId(UUID userId) {
        return loanRepository.findByUserId(userId);
    }

    @Override
    public Loan approveLoan(UUID loanId) {
        Loan loan = getLoanById(loanId);
        loan.setStatus(LoanStatus.APPROVED);
        return loanRepository.save(loan);
    }

    @Override
    public Loan rejectLoan(UUID loanId) {
        Loan loan = getLoanById(loanId);
        loan.setStatus(LoanStatus.REJECTED);
        return loanRepository.save(loan);
    }

    @Override
    public Loan payLoan(UUID loanId) {
        Loan loan = getLoanById(loanId);
        loan.setStatus(LoanStatus.PAID);
        return loanRepository.save(loan);
    }
}
