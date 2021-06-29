package ru.kiianov.foxminded.integerdivision.provider;

import ru.kiianov.foxminded.integerdivision.domain.DivisionResult;

public interface DivisionViewProvider {
    String provideView(DivisionResult divisionResult);
}
