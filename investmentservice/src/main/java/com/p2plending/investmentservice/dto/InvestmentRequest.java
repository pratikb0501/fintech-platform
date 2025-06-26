package com.p2plending.investmentservice.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class InvestmentRequest {
    private UUID userId;
    private UUID loanId;
    private double amount;
}
