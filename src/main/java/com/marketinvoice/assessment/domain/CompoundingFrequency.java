package com.marketinvoice.assessment.domain;

public enum CompoundingFrequency {
    MONTHLY(12), QUARTERLY(4), SEMIANNUALLY(2), ANNUALLY(1), WEEKLY(52);

    private final int frequency;

    CompoundingFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getFrequency() {
        return frequency;
    }
}
