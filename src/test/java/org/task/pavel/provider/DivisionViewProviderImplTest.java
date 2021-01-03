package org.task.pavel.provider;

import org.junit.jupiter.api.Test;
import org.task.pavel.domain.DivisionItem;
import org.task.pavel.domain.DivisionStep;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


class DivisionViewProviderImplTest {
    
    private final DivisionViewProvider divisionViewProvider = new DivisionViewProviderImpl();

    @Test
    void provideViewShouldReturnProperStringIfGetAllVariables() {
        List<DivisionStep> divisionSteps = new ArrayList<>();
        DivisionItem divisionItem = new DivisionItem(14342, 13);

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

        String actual = divisionViewProvider.provideView(divisionItem, divisionSteps);

        assertThat(expected, is(equalTo(actual)));
    }

    @Test
    void provideViewShouldReturnProperStringIfGetDivisorBiggerThanDividend() {
        List<DivisionStep> divisionSteps = new ArrayList<>();
        DivisionItem divisionItem = new DivisionItem(3, 10);

        divisionSteps.add(new DivisionStep(3, 3, 0));

        String expected =
                "_3|10\n" +
                        " 0|--\n" +
                        " -|0\n" +
                        " 3";

        String actual = divisionViewProvider.provideView(divisionItem, divisionSteps);

        assertThat(expected, is(equalTo(actual)));
    }

    @Test
    void provideViewShouldReturnProperStringIfGetDividend100008AndDivisor10() {
        List<DivisionStep> divisionSteps = new ArrayList<>();
        DivisionItem divisionItem = new DivisionItem(100008, 10);

        divisionSteps.add(new DivisionStep(0, 0, 10));

        String expected =
                "_100008|10\n" +
                " 10    |-----\n" +
                " --    |10000\n" +
                "  0";

        String actual = divisionViewProvider.provideView(divisionItem, divisionSteps);

        assertThat(expected, is(equalTo(actual)));
    }

    @Test
    void provideViewShouldReturnProperStringIfGetDividendZeroAndDivisorAboveZero() {
        List<DivisionStep> divisionSteps = new ArrayList<>();
        DivisionItem divisionItem = new DivisionItem(0, 5);

        divisionSteps.add(new DivisionStep(0, 0, 0));

        String expected =
                "_0|5\n" +
                " 0|-\n" +
                " -|0\n" +
                " 0";

        String actual = divisionViewProvider.provideView(divisionItem, divisionSteps);

        assertThat(expected, is(equalTo(actual)));
    }

    @Test
    void provideViewShouldReturnProperStringIfDivisorEqualsResultOfDivision() {
        List<DivisionStep> divisionSteps = new ArrayList<>();
        DivisionItem divisionItem = new DivisionItem(100, 10);

        divisionSteps.add(new DivisionStep(0, 10, 10));

        String expected =
                "_100|10\n" +
                " 10 |--\n" +
                " -- |10\n" +
                "  0";

        String actual = divisionViewProvider.provideView(divisionItem, divisionSteps);

        assertThat(expected, is(equalTo(actual)));
    }
}
