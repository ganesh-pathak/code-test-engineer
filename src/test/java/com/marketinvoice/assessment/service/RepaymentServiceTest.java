package com.marketinvoice.assessment.service;

import com.marketinvoice.assessment.domain.CompoundingFrequency;
import com.marketinvoice.assessment.domain.LoanSummary;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class RepaymentServiceTest {

    private RepaymentService repaymentService = new RepaymentService();

    @Test
    public void shouldCalculateRepaymentInfoFor52WeekTenureWithWeeklyCompounding() {
        // given
        BigDecimal loanAmount = new BigDecimal(50000);
        BigDecimal interestRate = new BigDecimal(19);
        // when
        LoanSummary loanSummary = repaymentService.calculateLoanSummary(loanAmount, interestRate, CompoundingFrequency.WEEKLY);
        // then
        assertThat(loanSummary.getWeeklyRepayment()).isEqualTo(new BigDecimal(1058));
        assertThat(loanSummary.getTotalRepaid()).isEqualTo(new BigDecimal(55016));
        assertThat(loanSummary.getTotalInterest()).isEqualTo(new BigDecimal(5016));
    }

}