package org.task.pavel.provider;

import org.task.pavel.domain.DivisionStep;

import java.util.List;

public interface DivisionMathProvider {
    List<DivisionStep> provideMathCalculation(int dividend, int divisor);
}
