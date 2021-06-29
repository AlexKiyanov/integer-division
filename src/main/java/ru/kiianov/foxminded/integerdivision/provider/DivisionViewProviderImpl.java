package ru.kiianov.foxminded.integerdivision.provider;

import ru.kiianov.foxminded.integerdivision.domain.DivisionResult;
import ru.kiianov.foxminded.integerdivision.domain.DivisionStep;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DivisionViewProviderImpl implements DivisionViewProvider {
    private int shift = 0;

    @Override
    public String provideView(DivisionResult divisionResult) {
        List<DivisionStep> steps;
        StringBuilder resultString = new StringBuilder();

        int dividend = divisionResult.getDividend();
        int divisor = divisionResult.getDivisor();
        steps = divisionResult.getSteps();
        int reminder;

        resultString.append(makeFirstLine(divisionResult));

        resultString.append(makeSecondLine(divisionResult));

        resultString.append(makeThirdLine(divisionResult));

        if (dividend < divisor) {
            return resultString.toString();
        }

        for (int i = 1; i < steps.size(); i++) {

            reminder = steps.get(i).getReminder();

            if (reminder != 0) {
                resultString.append(appendDivisionStep(i, divisionResult));
            } else {
                shift = calculateDigit(steps.get(0).getReminder()) + i - 1;
            }
        }
        shift = 0;

        resultString
                .append(appendLastMod(divisionResult));

        return resultString.toString();
    }

    private String makeFirstLine(DivisionResult divisionResult) {
        int dividend = divisionResult.getDividend();
        int divisor = divisionResult.getDivisor();
        return "_" +
                dividend +
                "│" +
                divisor +
                "\n";
    }

    private String makeSecondLine(DivisionResult divisionResult) {
        int dividend = divisionResult.getDividend();
        int multiplier = divisionResult.getSteps().get(0).getMultiplier();
        return String.format(" %-" + calculateDigit(dividend) + "s", multiplier) + "│" +
                makeDividerBetweenDivisorAndResult(divisionResult) + "\n";
    }

    private String makeDividerBetweenDivisorAndResult(DivisionResult divisionResult) {
        List<DivisionStep> steps = divisionResult.getSteps();
        return makeDivider((int) steps
                .stream()
                .map(DivisionStep::getQuotient)
                .count());
    }

    private String makeThirdLine(DivisionResult divisionResult) {
        int dividend = divisionResult.getDividend();
        int multiplier = divisionResult.getSteps().get(0).getMultiplier();
        return String
                .format(" %-" + calculateDigit(dividend) + "s",
                        makeDivider(calculateDigit(multiplier))) + "│" +
                        appendResult(divisionResult) + "\n";
    }

    private String appendResult(DivisionResult divisionResult) {
        List<DivisionStep> steps = divisionResult.getSteps();
        return steps
                .stream()
                .map(DivisionStep::getQuotient)
                .map(Objects::toString)
                .collect(Collectors.joining(""));
    }

    private String appendDivisionStep(int stepIndex, DivisionResult divisionResult) {
        StringBuilder divisionStepBuilder = new StringBuilder();
        List<DivisionStep> steps = divisionResult.getSteps();
        int reminder = steps.get(stepIndex).getReminder();
        int multiplier = steps.get(stepIndex).getMultiplier();
        int reminderDigit = calculateDigit(steps.get(stepIndex - 1).getReminder());
        int modDigit = calculateDigit(steps.get(stepIndex - 1).getMod());

        if (reminderDigit > modDigit) {
            shift += reminderDigit - modDigit;
        }

        divisionStepBuilder
                .append(assemblyString(shift, ' '))
                .append("_")
                .append(reminder)
                .append("\n")
                .append(assemblyString(shift + 1, ' '))
                .append(multiplier)
                .append("\n")
                .append(assemblyString(shift + 1, ' '))
                .append(makeDivider(calculateDigit(multiplier)))
                .append("\n");
        return divisionStepBuilder.toString();
    }

    private String appendLastMod(DivisionResult divisionResult) {
        int dividend = divisionResult.getDividend();
        List<DivisionStep> steps = divisionResult.getSteps();
        StringBuilder modBuilder = new StringBuilder();
        if (steps.get(steps.size() - 1).getMod() != 0) {
            modBuilder.append(assemblyString(calculateDigit(dividend) + 1 - calculateDigit(steps.get(steps.size() - 1).getMod()), ' '))
                    .append(steps.get(steps.size() - 1).getMod())
                    .append("\n");
        }
        return modBuilder.toString();
    }

    private String assemblyString(int numberOfSymbols, char symbol) {
        return String.valueOf(symbol).repeat(Math.max(0, numberOfSymbols));
    }

    private int calculateDigit(int i) {
        return Integer.toString(i).length();
    }

    private String makeDivider(int count) {
        return assemblyString(count, '-');
    }
}
