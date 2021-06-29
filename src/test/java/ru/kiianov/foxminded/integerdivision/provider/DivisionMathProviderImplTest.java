package ru.kiianov.foxminded.integerdivision.provider;

import org.junit.jupiter.api.Test;
import ru.kiianov.foxminded.integerdivision.domain.DivisionResult;
import ru.kiianov.foxminded.integerdivision.domain.DivisionStep;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DivisionMathProviderImplTest {
    private final DivisionMathProvider divisionMathProvider = new DivisionMathProviderImpl();

    @Test
    void provideMathDivisionShouldReturnCorrectDivisionResultForPowerOfTenNumbers() {
        final List<DivisionStep> steps = new ArrayList<>();
        steps.add(new DivisionStep(10, 1, 10, 0));
        steps.add(new DivisionStep(0, 0, 0, 0));
        steps.add(new DivisionStep(0, 0, 0, 0));

        final DivisionResult expectedDivisionResult = new DivisionResult(1000, 10, steps);

        assertEquals(expectedDivisionResult, divisionMathProvider.provideMathDivision(1000, 10));
    }

    @Test
    void provideMathDivisionShouldReturnCorrectDivisionResult() {
        final List<DivisionStep> steps = new ArrayList<>();
        steps.add(new DivisionStep(51, 6, 48, 3));
        steps.add(new DivisionStep(32, 4, 32, 0));

        final DivisionResult expectedDivisionResult = new DivisionResult(512, 8, steps);

        assertEquals(expectedDivisionResult, divisionMathProvider.provideMathDivision(512, 8));
    }

    @Test
    void provideMathDivisionShouldReturnCorrectDivisionResultIfDividendLessThanDivisor() {
        final List<DivisionStep> steps = new ArrayList<>();
        steps.add(new DivisionStep(15, 0, 0, 15));

        final DivisionResult expectedResult = new DivisionResult(15, 17, steps);

        assertEquals(expectedResult, divisionMathProvider.provideMathDivision(15, 17));
    }

    @Test
    void provideMathDivisionShouldReturnCorrectDivisionResultIfDividendEqualsDivisor() {
        final List<DivisionStep> steps = new ArrayList<>();
        steps.add(new DivisionStep(15555, 1, 15555, 0));

        final DivisionResult expectedResult = new DivisionResult(15555, 15555, steps);

        assertEquals(expectedResult, divisionMathProvider.provideMathDivision(15555, 15555));
    }

    @Test
    void provideMathDivisionShouldReturnCorrectDivisionResultIfReminderBiggerThanDivisor() {
        final List<DivisionStep> steps = new ArrayList<>();
        steps.add(new DivisionStep(2, 2, 2, 0));

        final DivisionResult expectedResult = new DivisionResult(2, 1, steps);

        assertEquals(expectedResult, divisionMathProvider.provideMathDivision(2, 1));
    }

}
