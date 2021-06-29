package ru.kiianov.foxminded.integerdivision.provider;

import ru.kiianov.foxminded.integerdivision.domain.DivisionResult;

public interface DivisionMathProvider {

    DivisionResult provideMathDivision(int division, int divisor);
}
