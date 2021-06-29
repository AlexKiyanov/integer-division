package ru.kiianov.foxminded.integerdivision;

import org.junit.jupiter.api.Test;
import ru.kiianov.foxminded.integerdivision.provider.DivisionMathProviderImpl;
import ru.kiianov.foxminded.integerdivision.provider.DivisionViewProviderImpl;
import ru.kiianov.foxminded.integerdivision.validator.DivisionValidatorImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;


class IntegerDivisionCalculatorITTest {

    IntegerDivisionCalculator calculator = new IntegerDivisionCalculator(
            new DivisionMathProviderImpl(),
            new DivisionValidatorImpl(),
            new DivisionViewProviderImpl());

    @Test
    void calculateDivisionShouldProvideCorrectResultForDivisionWithoutModulo() {
        String expected = "_512│8\n" +
                " 48 │--\n" +
                " -- │64\n" +
                " _32\n" +
                "  32\n" +
                "  --\n";

        assertEquals(expected, calculator.calculateDivision(512, 8));
    }

    @Test
    void calculateDivisionShouldProvideCorrectResultForDivisionNumbersMultipleTen() {
        String expected = "_10000│10\n" +
                " 10   │----\n" +
                " --   │1000\n";

        assertEquals(expected, calculator.calculateDivision(10000, 10));
    }

    @Test
    void calculateDivisionShouldProvideCorrectResultForDivisionWithModulo() {
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

        assertEquals(expected, calculator.calculateDivision(14789, 20));
    }

    @Test
    void calculateDivisionShouldProvideCorrectResultForBigIntegerNumbers() {
        String expected = "_746387456│23423\n" +
                " 70269    │-----\n" +
                " -----    │31865\n" +
                " _43697\n" +
                "  23423\n" +
                "  -----\n" +
                " _202744\n" +
                "  187384\n" +
                "  ------\n" +
                "  _153605\n" +
                "   140538\n" +
                "   ------\n" +
                "   _130676\n" +
                "    117115\n" +
                "    ------\n" +
                "     13561\n";

        assertEquals(expected, calculator.calculateDivision(746387456, 23423));
    }

    @Test
    void calculateDivisionShouldProvideCorrectResultIfDividendLessThanDivisor() {
        String expected = "_15│17\n" +
                " 0 │-\n" +
                " - │0\n";

        assertEquals(expected, calculator.calculateDivision(15, 17));
    }

    @Test
    void calculateDivisionShouldProvideCorrectResultIfDividendEqualsDivisor() {
        String expected = "_512│512\n" +
                " 512│-\n" +
                " ---│1\n";

        assertEquals(expected, calculator.calculateDivision(512, 512));
    }

    @Test
    void calculateDivisionShouldProvideCorrectResultIfDividendContainsManyZeroesWithModulo() {
        String expected = "_100010005│10\n" +
                " 10       │--------\n" +
                " --       │10001000\n" +
                "    _10\n" +
                "     10\n" +
                "     --\n" +
                "         5\n";

        assertEquals(expected, calculator.calculateDivision(100010005, 10));
    }

    @Test
    void calculateDivisionShouldProvideCorrectResultIfDividendContainsManyZeroes() {
        String expected = "_100010000│10\n" +
                " 10       │--------\n" +
                " --       │10001000\n" +
                "    _10\n" +
                "     10\n" +
                "     --\n";

        assertEquals(expected, calculator.calculateDivision(100010000, 10));
    }
}
