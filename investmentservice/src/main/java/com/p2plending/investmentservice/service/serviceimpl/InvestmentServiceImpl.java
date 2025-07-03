package com.p2plending.investmentservice.service.serviceimpl;

import com.p2plending.investmentservice.dto.InvestmentRequest;
import com.p2plending.investmentservice.dto.LoanResponse;
import com.p2plending.investmentservice.dto.LoanStatus;
import com.p2plending.investmentservice.dto.UpdateLoanStatusRequest;
import com.p2plending.investmentservice.model.Investment;
import com.p2plending.investmentservice.repository.InvestmentRepository;
import com.p2plending.investmentservice.service.InvestmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InvestmentServiceImpl implements InvestmentService {

    private final InvestmentRepository investmentRepository;

    @Override
    public Investment createInvestment(InvestmentRequest request) {
        if (request.getAmount() <= 0) {
            throw new IllegalArgumentException("Investment amount must be greater than zero.");
        }

        LoanResponse loan = fetchLoanDetails(request.getLoanId());

        if (loan == null || loan.getStatus() != LoanStatus.APPROVED) {
            throw new IllegalArgumentException("Loan not available for investment.");
        }

        if (BigDecimal.valueOf(request.getAmount()).compareTo(loan.getAmount()) != 0) {
            throw new IllegalArgumentException("Investment must match loan amount exactly.");
        }
        // Create and save investment
        Investment investment = Investment.builder()
                .userId(request.getUserId())
                .loanId(request.getLoanId())
                .amount(request.getAmount())
                .investedAt(java.time.LocalDateTime.now())
                .build();

        Investment savedInvestment = investmentRepository.save(investment);

        // TODO: Update loan's funded amount in Loan Service
        updateLoanStatusToFunded(request.getLoanId());

        return savedInvestment;
    }

    @Override
    public List<Investment> getInvestmentsByUserId(UUID userId) {
        return investmentRepository.findByUserId(userId);
    }

    @Override
    public List<Investment> getInvestmentsByLoanId(UUID loanId) {
        return investmentRepository.findByLoanId(loanId);
    }

    private final WebClient webClient = WebClient.create("http://loanservice-service:8082");

    private LoanResponse fetchLoanDetails(UUID loanId) {
        try {
            return webClient.get()
                    .uri("/api/v1/loans/" + loanId)
                    .retrieve()
                    .bodyToMono(LoanResponse.class)
                    .block();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch loan details from Loan Service.", e);
        }
    }

    private void updateLoanStatusToFunded(UUID loanId) {
        try {

            UpdateLoanStatusRequest request = new UpdateLoanStatusRequest(LoanStatus.FUNDED);

            webClient.put()
                    .uri("/api/v1/loans/" + loanId + "/status")
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(request)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
        } catch (Exception e) {
            throw new RuntimeException("Failed to update loan status to FUNDED in Loan Service.", e);
        }
    }
}
