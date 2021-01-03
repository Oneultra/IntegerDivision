package org.task.pavel.provider;

import org.task.pavel.domain.DivisionItem;
import org.task.pavel.domain.DivisionStep;

import java.util.List;

public class DivisionViewProviderImpl implements DivisionViewProvider {
    private static final String SPACE = " ";
    private static final String NEW_LINE = "\n";
    private static final String PIPE = "|";
    private static final String HYPHEN = "-";
    private static final String UNDERSCORE = "_";

    @Override
    public String provideView(DivisionItem divisionItem, List<DivisionStep> steps) {
        StringBuilder formattedResult = new StringBuilder();
        DivisionStep initialDivisionSteps = steps.get(0);

        if (divisionItem.getDividend() < divisionItem.getDivisor()) {
            return formattedResult.append(firstStepFormatter(divisionItem, initialDivisionSteps)).toString();
        }
        return formattedResult.append(secondStepFormatter(divisionItem, initialDivisionSteps))
                .append(lastStepFormatter(steps)).toString();
    }

    private String firstStepFormatter(DivisionItem divisionItem, DivisionStep firstDivisionStep) {
        StringBuilder firstStep = new StringBuilder();
        int dividendLength = String.valueOf(divisionItem.getDividend()).length();
        int divisorLength = String.valueOf(divisionItem.getDivisor()).length();

        return firstStep.append(UNDERSCORE)
                .append(divisionItem.getDividend())
                .append(PIPE)
                .append(divisionItem.getDivisor())
                .append(NEW_LINE)
                .append(SPACE)
                .append(firstDivisionStep.getDividerMultiple())
                .append(insertSign(SPACE, dividendLength - 1))
                .append(PIPE)
                .append(insertSign(HYPHEN, divisorLength))
                .append(NEW_LINE)
                .append(SPACE)
                .append(HYPHEN)
                .append(insertSign(SPACE, dividendLength - 1))
                .append(PIPE)
                .append(firstDivisionStep.getDividerMultiple())
                .append(NEW_LINE)
                .append(SPACE)
                .append(divisionItem.getDividend())
                .toString();
    }

    private String secondStepFormatter(DivisionItem divisionItem, DivisionStep secondDivisionStep) {
        StringBuilder secondStep = new StringBuilder();

        int result = divisionItem.getDividend() / divisionItem.getDivisor();
        int dividendLength = String.valueOf(divisionItem.getDividend()).length();
        int resultLength = String.valueOf(result).length();
        int closestMultiplierLength = String.valueOf(secondDivisionStep.getDividerMultiple()).length();
        int spaces = dividendLength - closestMultiplierLength;

        return secondStep.append(UNDERSCORE)
                .append(divisionItem.getDividend())
                .append(PIPE)
                .append(divisionItem.getDivisor())
                .append(NEW_LINE)
                .append(SPACE)
                .append(secondDivisionStep.getDividerMultiple())
                .append(insertSign(SPACE, spaces))
                .append(PIPE)
                .append(insertSign(HYPHEN, resultLength))
                .append(NEW_LINE)
                .append(SPACE)
                .append(insertSign(HYPHEN, closestMultiplierLength))
                .append(insertSign(SPACE, spaces))
                .append(PIPE)
                .append(result)
                .append(NEW_LINE)
                .toString();
    }

    private String lastStepFormatter(List<DivisionStep> divisionSteps) {
        StringBuilder thirdStep = new StringBuilder();

        int reminderLength = String.valueOf(divisionSteps.get(0).getRemainder()).length();
        int secondStepClosestMultiplierLength = String.valueOf(divisionSteps.get(0).getDividerMultiple()).length();
        int indent = secondStepClosestMultiplierLength - reminderLength + 1;
        DivisionStep lastDivisionStep = divisionSteps.get(divisionSteps.size() - 1);

        for (int i = 1; i < divisionSteps.size(); i++) {

            DivisionStep divisionStep = divisionSteps.get(i);
            int closestMultiplierLength = String.valueOf(divisionStep.getDividerMultiple()).length();

            thirdStep.append(insertSign(SPACE, indent - 1))
                    .append(UNDERSCORE)
                    .append(divisionStep.getQuotient())
                    .append(NEW_LINE)
                    .append(insertSign(SPACE, indent))
                    .append(divisionStep.getDividerMultiple())
                    .append(NEW_LINE)
                    .append(insertSign(SPACE, indent))
                    .append(insertSign(HYPHEN, closestMultiplierLength))
                    .append(NEW_LINE);

            indent = (insertSign(SPACE, indent) +
                    insertSign(HYPHEN, closestMultiplierLength)).length()
                    - String.valueOf(divisionStep.getRemainder()).length();
        }
        return thirdStep.append(insertSign(SPACE, indent))
                .append(lastDivisionStep.getRemainder()).toString();
    }

    private String insertSign(String insertSign, int quantity) {
        StringBuilder signInsertion = new StringBuilder();

        for (int i = 0; i < quantity; i++) {
            signInsertion.append(insertSign);
        }
        return signInsertion.toString();
    }
}
