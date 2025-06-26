package com.p2plending.investmentservice.repository;

import com.p2plending.investmentservice.model.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InvestmentRepository extends JpaRepository<Investment,UUID> {
    List<Investment> findByUserId(UUID userId);
    List<Investment> findByLoanId(UUID loanId);
}
