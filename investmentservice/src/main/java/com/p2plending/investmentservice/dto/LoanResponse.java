package com.p2plending.investmentservice.dto;

import com.p2plending.investmentservice.dto.LoanStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class LoanResponse {
    private UUID id;
    private UUID userId;
    private BigDecimal amount;
    private Integer tenure;
    private Double interestRate;
    private LoanStatus status;
    private LocalDateTime createdAt;
}
