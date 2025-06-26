package com.p2plending.investmentservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class InvestmentResponse {
    private UUID id;
    private UUID userId;
    private UUID loanId;
    private double amount;
    private LocalDateTime investedAt;
}
