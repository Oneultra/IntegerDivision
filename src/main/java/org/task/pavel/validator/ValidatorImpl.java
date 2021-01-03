package org.task.pavel.validator;

public class ValidatorImpl implements Validator {

    @Override
    public void validate(int dividend, int divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("Divisor is " + divisor);
        }

        if (divisor < 0) {
            throw new IllegalArgumentException("Divisor is below 0. Divisor = " + divisor );
        }

        if (dividend < 0) {
            throw new IllegalArgumentException("Dividend is bellow 0. Dividend = " + dividend);
        }
    }
}
