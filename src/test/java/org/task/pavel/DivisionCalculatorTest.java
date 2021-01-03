package org.task.pavel;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.task.pavel.domain.DivisionItem;
import org.task.pavel.domain.DivisionStep;
import org.task.pavel.provider.DivisionMathProvider;
import org.task.pavel.provider.DivisionViewProvider;
import org.task.pavel.validator.Validator;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class DivisionCalculatorTest {

    @Mock
    private Validator mockedValidator;

    @Mock
    private DivisionMathProvider mockedDivisionMathProvider;

    @Mock
    private DivisionViewProvider mockedDivisionViewProvider;

    @InjectMocks
    private DivisionCalculator divisionCalculator;

    @Test
    void calculateShouldReturnCorrectStringWhenGetAllParameters() {
        List<DivisionStep> divisionSteps = new ArrayList<>();

        divisionSteps.add(new DivisionStep(1, 14, 13));
        divisionSteps.add(new DivisionStep(0, 13, 13));
        divisionSteps.add(new DivisionStep(3, 42, 39));

        String expected =
                "_14342|13\n" +
                        " 13   |----\n" +
                        " --   |1103\n" +
                        " _13\n" +
                        "  13\n" +
                        "  --\n" +
                        "  _42\n" +
                        "   39\n" +
                        "   --\n" +
                        "    3";

        when(mockedDivisionMathProvider.provideMathCalculation(anyInt(), anyInt())).thenReturn(divisionSteps);
        when(mockedDivisionViewProvider.provideView(any(DivisionItem.class), anyList())).thenReturn(expected);

        String actual = divisionCalculator.calculate(72621, 14);

        assertThat(expected, is(equalTo(actual)));

        verify(mockedValidator).validate(anyInt(), anyInt());
        verify(mockedDivisionMathProvider).provideMathCalculation(anyInt(), anyInt());
        verify(mockedDivisionViewProvider).provideView(any(DivisionItem.class), anyList());
    }

    @Test
    void ifValidatorThrowsIllegalArgumentExceptionCalculateShouldNotStarts() {
        doThrow(IllegalArgumentException.class).when(mockedValidator).validate(anyInt(), anyInt());

        assertThrows(IllegalArgumentException.class, () -> divisionCalculator.calculate(534, 0));

        verify(mockedValidator).validate(anyInt(), anyInt());
        verifyNoMoreInteractions(mockedDivisionMathProvider, mockedDivisionMathProvider);
    }
}
