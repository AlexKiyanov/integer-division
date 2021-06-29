package ru.kiianov.foxminded.integerdivision;

import ru.kiianov.foxminded.integerdivision.domain.DivisionResult;
import ru.kiianov.foxminded.integerdivision.provider.DivisionMathProvider;
import ru.kiianov.foxminded.integerdivision.provider.DivisionViewProvider;
import ru.kiianov.foxminded.integerdivision.validator.DivisionValidator;

public class IntegerDivisionCalculator {
    private final DivisionMathProvider divisionMathProvider;
    private final DivisionValidator divisionValidator;
    private final DivisionViewProvider divisionViewProvider;

    public IntegerDivisionCalculator(DivisionMathProvider divisionMathProvider, DivisionValidator divisionValidator,
                                     DivisionViewProvider divisionViewProvider) {
        this.divisionMathProvider = divisionMathProvider;
        this.divisionValidator = divisionValidator;
        this.divisionViewProvider = divisionViewProvider;
    }

    public String calculateDivision(int division, int divisor) {
        divisionValidator.validate(division, divisor);

        DivisionResult divisionResult = divisionMathProvider.provideMathDivision(division, divisor);

        return divisionViewProvider.provideView(divisionResult);
    }
}
