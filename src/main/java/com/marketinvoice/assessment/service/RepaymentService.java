package com.marketinvoice.assessment.service;

import com.marketinvoice.assessment.domain.CompoundingFrequency;
import com.marketinvoice.assessment.domain.Installment;
import com.marketinvoice.assessment.domain.LoanSummary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.acl.AclEntry;
import java.util.ArrayList;
import java.util.List;

@Service
public class RepaymentService {

    public LoanSummary calculateLoanSummary(BigDecimal loanAmount, BigDecimal interest, CompoundingFrequency compoundingFrequency) {
        int installments = compoundingFrequency.getFrequency();
        BigDecimal interestRate = interest.divide(BigDecimal.valueOf(installments).multiply(BigDecimal.valueOf(100)), 6, RoundingMode.UP);
        BigDecimal periodicInterestRate = BigDecimal.ONE.divide(BigDecimal.ONE.add(interestRate).pow(installments), 6, RoundingMode.UP);
        BigDecimal weeklyRepayment = loanAmount.multiply(interestRate).divide(BigDecimal.ONE.subtract(periodicInterestRate), 6, RoundingMode.UP);
        BigDecimal totalRepaid = weeklyRepayment.setScale(0, RoundingMode.UP).multiply(BigDecimal.valueOf(installments));

        return LoanSummary.builder()
                .totalInterest(totalRepaid.subtract(loanAmount))
                .weeklyRepayment(weeklyRepayment.setScale(0, RoundingMode.UP))
                .totalRepaid(totalRepaid).build();
    }

    public List<Installment> calculateRepaymentSchedule(BigDecimal loanAmount, BigDecimal interest) {
        CompoundingFrequency compoundingFrequency = CompoundingFrequency.WEEKLY;
        BigDecimal weeklyRepayment = calculateLoanSummary(loanAmount, interest, compoundingFrequency).getWeeklyRepayment();
        BigDecimal interestRate = interest.divide(BigDecimal.valueOf(compoundingFrequency.getFrequency()).multiply(BigDecimal.valueOf(100)), 8, RoundingMode.UP);

        List<Installment> installments = new ArrayList<>();
        BigDecimal firstInstallmentInterest = loanAmount.multiply(interestRate);
        installments.add(createInstallment(weeklyRepayment, loanAmount, firstInstallmentInterest, 1));

        for (int i = 1; i < compoundingFrequency.getFrequency(); i++) {
            Installment previousInstallment = installments.get(i - 1);
            BigDecimal amountDue = previousInstallment.getAmountDue().subtract(previousInstallment.getPrincipal());
            BigDecimal installmentInterest = amountDue.multiply(interestRate);
            installments.add(createInstallment(weeklyRepayment, amountDue, installmentInterest, i + 1));
        }
        return installments;
    }

    private Installment createInstallment(BigDecimal weeklyRepayment, BigDecimal amountDue, BigDecimal installmentInterest, int installmentNumber) {
        return Installment.builder()
                .installmentNumber(installmentNumber)
                .amountDue(amountDue)
                .interest(installmentInterest)
                .principal(weeklyRepayment.subtract(installmentInterest))
                .build();
    }
}
