package com.p2plending.loanservice.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class LoanRequest {

    @NotNull(message = "User ID is required")
    private UUID userId;

    @NotNull(message = "Loan amount is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than 0")
    private BigDecimal amount;

    @NotNull(message = "Loan term is required")
    @Min(value = 1, message = "Term must be at least 1 month")
    private Integer tenure;

    @NotNull(message = "Interest rate is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Interest rate must be greater than 0")
    private Double interestRate;
}
