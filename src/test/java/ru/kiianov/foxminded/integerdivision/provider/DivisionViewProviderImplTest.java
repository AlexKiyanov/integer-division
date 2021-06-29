package ru.kiianov.foxminded.integerdivision.provider;

import org.junit.jupiter.api.Test;
import ru.kiianov.foxminded.integerdivision.domain.DivisionResult;
import ru.kiianov.foxminded.integerdivision.domain.DivisionStep;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DivisionViewProviderImplTest {
    final DivisionViewProvider viewProvider = new DivisionViewProviderImpl();

    @Test
    void provideViewShouldProvideCorrectDivisionView() {
        final List<DivisionStep> steps = new ArrayList<>();

        steps.add(new DivisionStep(147, 7, 140, 7));
        steps.add(new DivisionStep(78, 3, 60, 18));
        steps.add(new DivisionStep(189, 9, 180, 9));

        final DivisionResult result = new DivisionResult(14789, 20, steps);

        String expected = "_14789│20\n" +
                " 140  │---\n" +
                " ---  │739\n" +
                "  _78\n" +
                "   60\n" +
                "   --\n" +
                "  _189\n" +
                "   180\n" +
                "   ---\n" +
                "     9\n";

        assertEquals(expected, viewProvider.provideView(result));
    }

    @Test
    void provideViewShouldProvideCorrectDivisionViewIfDividendContainsManyZeroes() {
        final List<DivisionStep> steps = new ArrayList<>();

        steps.add(new DivisionStep(10, 1, 10, 0));
        steps.add(new DivisionStep(0, 0, 0, 0));
        steps.add(new DivisionStep(0, 0, 0, 0));
        steps.add(new DivisionStep(0, 0, 0, 1));
        steps.add(new DivisionStep(10, 1, 10, 0));
        steps.add(new DivisionStep(0, 0, 0, 0));
        steps.add(new DivisionStep(0, 0, 0, 0));
        steps.add(new DivisionStep(0, 0, 0, 0));

        final DivisionResult result = new DivisionResult(100010000, 10, steps);

        String expected = "_100010000│10\n" +
                " 10       │--------\n" +
                " --       │10001000\n" +
                "    _10\n" +
                "     10\n" +
                "     --\n";

        assertEquals(expected, viewProvider.provideView(result));
    }

    @Test
    void provideViewShouldProvideCorrectDivisionViewIfDividendLessThanDivisor() {
        final List<DivisionStep> steps = new ArrayList<>();

        steps.add(new DivisionStep(0, 0, 0, 0));

        DivisionResult result = new DivisionResult(15, 17, steps);

        String expected = "_15│17\n" +
                " 0 │-\n" +
                " - │0\n";

        assertEquals(expected, viewProvider.provideView(result));
    }
}
