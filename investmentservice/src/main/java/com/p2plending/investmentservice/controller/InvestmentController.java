package com.p2plending.investmentservice.controller;

import com.p2plending.investmentservice.dto.InvestmentRequest;
import com.p2plending.investmentservice.dto.InvestmentResponse;
import com.p2plending.investmentservice.model.Investment;
import com.p2plending.investmentservice.service.InvestmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/investments")
public class InvestmentController {
    private final InvestmentService investmentService;

    @PostMapping("/create")
    public InvestmentResponse create(@RequestBody InvestmentRequest request) {
        Investment investment = investmentService.createInvestment(request);
        return toResponse(investment);
    }

    @GetMapping("/user/{userId}")
    public List<InvestmentResponse> getByUser(@PathVariable UUID userId) {
        return investmentService.getInvestmentsByUserId(userId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/loan/{loanId}")
    public List<InvestmentResponse> getByLoan(@PathVariable UUID loanId) {
        return investmentService.getInvestmentsByLoanId(loanId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private InvestmentResponse toResponse(Investment investment) {
        return InvestmentResponse.builder()
                .id(investment.getId())
                .userId(investment.getUserId())
                .loanId(investment.getLoanId())
                .amount(investment.getAmount())
                .investedAt(investment.getInvestedAt())
                .build();
    }
}
