package com.p2plending.loanservice.repository;

import com.p2plending.loanservice.model.Loan;
import com.p2plending.loanservice.model.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface LoanRepository extends JpaRepository<Loan, UUID> {
    
    List<Loan> findByUserId(UUID userId);
    
    List<Loan> findByStatus(LoanStatus status);

    List<Loan> findLoanByCreatedAt(LocalDateTime createdAt);
}