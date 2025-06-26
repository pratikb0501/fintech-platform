package com.p2plending.investmentservice.service;

import com.p2plending.investmentservice.model.Investment;
import com.p2plending.investmentservice.dto.InvestmentRequest;

import java.util.List;
import java.util.UUID;


public interface InvestmentService {
    Investment createInvestment(InvestmentRequest request);
    List<Investment> getInvestmentsByUserId(UUID userId);
    List<Investment> getInvestmentsByLoanId(UUID loanId);
}
