package com.marketinvoice.assessment.application;

import com.marketinvoice.assessment.domain.CompoundingFrequency;
import com.marketinvoice.assessment.domain.Installment;
import com.marketinvoice.assessment.domain.LoanSummary;
import com.marketinvoice.assessment.service.RepaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class LoanController {

    @Autowired
    RepaymentService repaymentService;

    @GetMapping("/loansummary")
    public LoanSummary loanSummary(@RequestParam BigDecimal amount, @RequestParam BigDecimal apr) {
        return repaymentService.calculateLoanSummary(amount, apr, CompoundingFrequency.WEEKLY);
    }

    @GetMapping("/repaymentSchedule")
    public List<Installment> repaymentSchedule(@RequestParam BigDecimal amount, @RequestParam BigDecimal apr) {
        return repaymentService.calculateRepaymentSchedule(amount, apr);
    }
}
