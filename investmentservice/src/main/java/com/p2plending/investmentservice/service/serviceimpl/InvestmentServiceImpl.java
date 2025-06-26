package com.p2plending.investmentservice.service.serviceimpl;

import com.p2plending.investmentservice.dto.InvestmentRequest;
import com.p2plending.investmentservice.model.Investment;
import com.p2plending.investmentservice.repository.InvestmentRepository;
import com.p2plending.investmentservice.service.InvestmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvestmentServiceImpl implements InvestmentService {

    private final InvestmentRepository investmentRepository;

    @Override
    public Investment createInvestment(InvestmentRequest request) {
        return null;
    }

    @Override
    public List<Investment> getInvestmentsByUserId(UUID userId) {
        return investmentRepository.findByUserId(userId);
    }

    @Override
    public List<Investment> getInvestmentsByLoanId(UUID loanId) {
        return investmentRepository.findByLoanId(loanId);
    }
}
