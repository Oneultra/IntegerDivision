package org.task.pavel.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidatorImplTest {

       private final Validator validator = new ValidatorImpl();

    @Test
    void validatorShouldReturnIllegalArgumentExceptionIfDivisorEqualsZero() {        
        assertThrows(IllegalArgumentException.class, () -> validator.validate(2845, 0),
                "Divisor = 0. Divisor is zero");
    }

    @Test
    void validatorShouldReturnIllegalArgumentExceptionIfDivisorBellowZero() {
        assertThrows(IllegalArgumentException.class, () -> validator.validate(645, -2),
                "Divisor < 0. Divisor is below zero");
    }

    @Test
    void validatorShouldReturnIllegalArgumentExceptionIfDividendBellowZero() {
        assertThrows(IllegalArgumentException.class, () -> validator.validate(-276, 2),
                "Dividend < 0. Dividend is bellow zero");
    }

    @Test
    void validatorShouldNotReturnExceptionIfDividendEqualsZero() {
        assertDoesNotThrow(() -> validator.validate(0, 50));
    }
}
