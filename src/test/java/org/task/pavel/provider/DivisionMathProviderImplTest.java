package org.task.pavel.provider;

import org.junit.jupiter.api.Test;
import org.task.pavel.domain.DivisionStep;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

class DivisionMathProviderImplTest {

    private final DivisionMathProvider divisionMathProvider = new DivisionMathProviderImpl();

    @Test
    void provideMathCalculationShouldReturnListOfStepsWhenDividendContainsFiveDigits() {
        List<DivisionStep> expected = new ArrayList<>();
        expected.add(new DivisionStep(3, 7, 4));
        expected.add(new DivisionStep(2, 38, 36));
        expected.add(new DivisionStep(1, 29, 28));
        expected.add(new DivisionStep(2, 14, 12));
        expected.add(new DivisionStep(1, 25, 24));

        List<DivisionStep> actual = divisionMathProvider.provideMathCalculation(78945, 4);

        assertThat(actual, containsInAnyOrder(expected.toArray()));
    }

    @Test
    void provideMathCalculationShouldReturnListOfStepsWhenDividendContainsFourDigits() {
        List<DivisionStep> expected = new ArrayList<>();
        expected.add(new DivisionStep(1, 43, 42));
        expected.add(new DivisionStep(2, 16, 14));
        expected.add(new DivisionStep(0, 21, 21));

        List<DivisionStep> actual = divisionMathProvider.provideMathCalculation(4361, 7);

        assertThat(actual, containsInAnyOrder(expected.toArray()));
    }

    @Test
    void provideMathCalculationShouldReturnListOfStepsWhenDividendContainsThreeDigits() {
        List<DivisionStep> expected = new ArrayList<>();
        expected.add(new DivisionStep(1, 25, 24));
        expected.add(new DivisionStep(1, 16, 15));

        List<DivisionStep> actual = divisionMathProvider.provideMathCalculation(256, 3);

        assertThat(actual, containsInAnyOrder(expected.toArray()));
    }

    @Test
    void provideMathCalculationShouldReturnListOfStepsWhenDividendContainsTwoDigits() {
        List<DivisionStep> expected = new ArrayList<>();
        expected.add(new DivisionStep(0, 3, 3));
        expected.add(new DivisionStep(2, 8, 6));

        List<DivisionStep> actual = divisionMathProvider.provideMathCalculation(38, 3);

        assertThat(actual, containsInAnyOrder(expected.toArray()));
    }

    @Test
    void provideMathCalculationShouldReturnListOfStepsWhenDividendDigitsEqualsDivisor() {
        List<DivisionStep> expected = new ArrayList<>();
        expected.add(new DivisionStep(0, 256, 256));

        List<DivisionStep> actual = divisionMathProvider.provideMathCalculation(256, 256);

        assertThat(actual, containsInAnyOrder(expected.toArray()));
    }

    @Test
    void provideMathCalculationShouldReturnListOfStepsWhenDividendEqualsZero() {
        List<DivisionStep> expected = new ArrayList<>();
        expected.add(new DivisionStep(0, 0, 0));

        List<DivisionStep> actual = divisionMathProvider.provideMathCalculation(0, 5);

        assertThat(actual, containsInAnyOrder(expected.toArray()));
    }

    @Test
    void provideMathCalculationShouldReturnListOfStepsWhenDividendSmallerThenDivisor() {
        List<DivisionStep> expected = new ArrayList<>();
        expected.add(new DivisionStep(25, 25, 0));

        List<DivisionStep> actual = divisionMathProvider.provideMathCalculation(25, 100);

        assertThat(actual, containsInAnyOrder(expected.toArray()));
    }
}
