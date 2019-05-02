package com.marketinvoice.assessment.validator;

import java.math.BigDecimal;
import java.util.function.Function;

public interface LoanAmountValidator extends Function<BigDecimal, Boolean> {

    static LoanAmountValidator amountInBetween1000And15000() {
        return amount -> amount.intValue() >= 1000 && amount.intValue() <= 15000;
    }

    static LoanAmountValidator amountInMultipleOfHundred() {
        return amount -> amount.remainder(BigDecimal.valueOf(100)).intValue() == 0;
    }

    default LoanAmountValidator and(LoanAmountValidator other) {
        return amount -> this.apply(amount) && other.apply(amount);
    }

}
