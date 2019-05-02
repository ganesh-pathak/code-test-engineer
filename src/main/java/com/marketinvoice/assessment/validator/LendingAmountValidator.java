package com.marketinvoice.assessment.validator;

import java.math.BigDecimal;
import java.util.function.BiFunction;

public interface LendingAmountValidator extends BiFunction<BigDecimal, BigDecimal, Boolean> {

    static LendingAmountValidator loanAmountGreaterThanLendingAmount() {
        return (loanAmount, lendingAmount) -> loanAmount.compareTo(lendingAmount) == 1;
    }

}
