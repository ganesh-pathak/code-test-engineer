package com.marketinvoice.assessment.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.marketinvoice.assessment.config.BigDecimalSerializer;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class Installment {

    private Integer installmentNumber;

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal amountDue;

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal principal;

    @JsonSerialize(using = BigDecimalSerializer.class)
    private BigDecimal interest;

}
