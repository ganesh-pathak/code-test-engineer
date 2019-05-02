package com.marketinvoice.assessment.domain;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class LoanSummary {

    private BigDecimal weeklyRepayment = BigDecimal.ZERO;
    private BigDecimal totalRepaid = BigDecimal.ZERO;
    private BigDecimal totalInterest = BigDecimal.ZERO;

    public BigDecimal getTotalInterest() {
        return totalInterest;
    }

    public BigDecimal getWeeklyRepayment() {
        return weeklyRepayment;
    }

    public BigDecimal getTotalRepaid() {
        return totalRepaid;
    }

}
