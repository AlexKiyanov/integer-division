package ru.kiianov.foxminded.integerdivision.provider;

import ru.kiianov.foxminded.integerdivision.domain.DivisionResult;
import ru.kiianov.foxminded.integerdivision.domain.DivisionStep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class DivisionMathProviderImpl implements DivisionMathProvider {

    @Override
    public DivisionResult provideMathDivision(int dividend, int divisor) {
        final List<DivisionStep> steps = new ArrayList<>();
        final List<String> dividendDigits = splitNumberToDigits(dividend);

        if (dividend < divisor) {
            steps.add(calculateStep(dividend, divisor));
            return new DivisionResult(dividend, divisor, steps);
        }

        final String partialQuotient = calculatePartialQuotient(dividend, divisor);
        steps.add(calculateStep(Integer.parseInt(partialQuotient), divisor));

        StringBuilder reminderBuilder = new StringBuilder();
        int reminder;
        int stepsIndex = 0;

        for (int i = partialQuotient.length(); i < dividendDigits.size(); i++) {
            reminderBuilder
                    .append(steps.get(stepsIndex).getMod())
                    .append(dividendDigits.get(i));
            reminder = Integer.parseInt(reminderBuilder.toString());

            if (reminder < divisor) {
                steps.add(calculateStep(reminder));
            } else {
                steps.add(calculateStep(reminder, divisor));
            }
            stepsIndex++;
            reminderBuilder.delete(0, reminderBuilder.length());
        }

        return new DivisionResult(dividend, divisor, steps);
    }

    private DivisionStep calculateStep(int reminder, int divisor) {
        final int quotient = reminder / divisor;
        final int multiplier = divisor * quotient;
        final int mod = reminder % divisor;
        return new DivisionStep(reminder, quotient, multiplier, mod);
    }

    private DivisionStep calculateStep(int mod) {
        return new DivisionStep(0, 0, 0, mod);
    }

    private String calculatePartialQuotient(int dividend, int divisor) {
        List<String> dividendDigits = splitNumberToDigits(dividend);

        StringBuilder partialQuotientBuilder = new StringBuilder();
        int i = 0;
        int reminder = Integer.parseInt(dividendDigits.get(0));
        if (reminder >= divisor) {
            return Integer.toString(reminder);
        }
        while (reminder < divisor) {
            partialQuotientBuilder.append(dividendDigits.get(i));
            reminder = Integer.parseInt(partialQuotientBuilder.toString());
            i++;
        }
        return partialQuotientBuilder.toString();
    }

    private List<String> splitNumberToDigits(int number) {
        return Arrays.stream(Integer.toString(number)
                .split(""))
                .collect(Collectors.toList());
    }
}
