package org.task.pavel;

import org.task.pavel.domain.DivisionItem;
import org.task.pavel.domain.DivisionStep;
import org.task.pavel.provider.DivisionMathProvider;
import org.task.pavel.provider.DivisionViewProvider;
import org.task.pavel.validator.Validator;

import java.util.List;

public class DivisionCalculator {
    private final Validator validator;
    private final DivisionMathProvider divisionMathProvider;
    private final DivisionViewProvider divisionViewProvider;

    public DivisionCalculator(Validator validator, DivisionMathProvider divisionMathProvider,
                              DivisionViewProvider divisionViewProvider) {
        this.validator = validator;
        this.divisionMathProvider = divisionMathProvider;
        this.divisionViewProvider = divisionViewProvider;
    }

    public String calculate(int dividend, int divisor) {
        validator.validate(dividend, divisor);
        List<DivisionStep> divisionSteps = divisionMathProvider.provideMathCalculation(dividend, divisor);

        return divisionViewProvider.provideView(new DivisionItem(dividend, divisor), divisionSteps);
    }
}
