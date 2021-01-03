package org.task.pavel.provider;

import org.task.pavel.domain.DivisionStep;

import java.util.ArrayList;
import java.util.List;

public class DivisionMathProviderImpl implements DivisionMathProvider {

    public static final int SPLIT_NUMBER = 10;

    @Override
    public List<DivisionStep> provideMathCalculation(int dividend, int divisor) {
        List<DivisionStep> divisionSteps = new ArrayList<>();
        int[] dividendDigits = splitDividend(dividend);

        if (dividend < divisor) {
            divisionSteps.add(new DivisionStep(dividend, dividend, 0));
            return divisionSteps;
        }

        int remainder = 0;

        for (int digit : dividendDigits) {
            int quotient = remainder * 10 + digit;
            int dividerMultiple = quotient - (quotient % divisor);

            if (digit < divisor) {
                remainder = quotient;
            }

            if (quotient >= divisor) {
                remainder = quotient - dividerMultiple;
                divisionSteps.add(new DivisionStep(remainder, quotient, dividerMultiple));
            }
        }

        return divisionSteps;
    }

    private int[] splitDividend(int dividend) {
        int dividendLength = String.valueOf(dividend).length();
        int[] dividendDigits = new int[dividendLength];

        for (int i = dividendLength - 1; i >= 0; i--) {
            dividendDigits[i] = dividend % SPLIT_NUMBER;
            dividend = dividend / SPLIT_NUMBER;
        }
        return dividendDigits;
    }
}
