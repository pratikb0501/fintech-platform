package com.p2plending.loanservice.controller;

import com.p2plending.loanservice.dto.LoanRequest;
import com.p2plending.loanservice.dto.LoanResponse;
import com.p2plending.loanservice.model.Loan;
import com.p2plending.loanservice.service.LoanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/loans")
public class LoanController {

    private final LoanService loanService;

    @PostMapping
    public LoanResponse createLoan(@Valid @RequestBody LoanRequest loanRequest) {
        Loan loan = loanService.createLoan(loanRequest);
        return mapToResponse(loan);
    }

    @GetMapping("/{id}")
    public LoanResponse getLoanById(@PathVariable("id") UUID id) {
        Loan loan = loanService.getLoanById(id);
        return mapToResponse(loan);
    }

    @GetMapping("/user/{userId}")
    public List<LoanResponse> getLoansByUserId(@PathVariable("userId") UUID userId) {
        List<Loan> loans = loanService.getLoansByUserId(userId);
        return loans.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @PostMapping("/{id}/approve")
    public LoanResponse approveLoan(@PathVariable("id") UUID id) {
        Loan loan = loanService.approveLoan(id);
        return mapToResponse(loan);
    }

    @PostMapping("/{id}/reject")
    public LoanResponse rejectLoan(@PathVariable("id") UUID id) {
        Loan loan = loanService.rejectLoan(id);
        return mapToResponse(loan);
    }

    @PostMapping("/{id}/pay")
    public LoanResponse payLoan(@PathVariable("id") UUID id) {
        Loan loan = loanService.payLoan(id);
        return mapToResponse(loan);
    }



    private LoanResponse mapToResponse(Loan loan) {
        return LoanResponse.builder()
                .id(loan.getId())
                .userId(loan.getUserId())
                .amount(loan.getAmount())
                .tenure(loan.getTenure())
                .interestRate(loan.getInterestRate())
                .status(loan.getStatus())
                .createdAt(loan.getCreatedAt())
                .build();
    }
}
