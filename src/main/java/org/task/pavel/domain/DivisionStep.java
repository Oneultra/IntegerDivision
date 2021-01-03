package org.task.pavel.domain;

import java.util.Objects;

public class DivisionStep {
    private final int remainder;
    private final int quotient;
    private final int dividerMultiple;

    public DivisionStep(int remainder, int quotient, int dividerMultiple) {
        this.remainder = remainder;
        this.quotient = quotient;
        this.dividerMultiple = dividerMultiple;
    }

    public int getRemainder() {
        return remainder;
    }

    public int getQuotient() {
        return quotient;
    }

    public int getDividerMultiple() {
        return dividerMultiple;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DivisionStep that = (DivisionStep) o;
        return remainder == that.remainder &&
                quotient == that.quotient &&
                dividerMultiple == that.dividerMultiple;
    }

    @Override
    public int hashCode() {
        return Objects.hash(remainder, quotient, dividerMultiple);
    }

    @Override
    public String toString() {
        return "DivisionStep: " +
                "remainder = " + remainder +
                ", quotient = " + quotient +
                ", dividerMultiple = " + dividerMultiple;
    }
}
