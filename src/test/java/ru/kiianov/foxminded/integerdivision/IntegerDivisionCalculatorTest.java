package ru.kiianov.foxminded.integerdivision;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.kiianov.foxminded.integerdivision.domain.DivisionResult;
import ru.kiianov.foxminded.integerdivision.domain.DivisionStep;
import ru.kiianov.foxminded.integerdivision.provider.DivisionMathProvider;
import ru.kiianov.foxminded.integerdivision.provider.DivisionViewProvider;
import ru.kiianov.foxminded.integerdivision.validator.DivisionValidator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IntegerDivisionCalculatorTest {

    @Mock
    DivisionMathProvider mathProvider;
    @Mock
    DivisionViewProvider viewProvider;
    @Mock
    DivisionValidator validator;

    @InjectMocks
    IntegerDivisionCalculator calculator;

    @Test
    void calculateDivisionShouldReturnResult() {
        String expected = "_512│8\n" +
                " 48 │--\n" +
                " -- │64\n" +
                " _32\n" +
                "  32\n" +
                "  --\n";
        List<DivisionStep> steps = new ArrayList<>();
        steps.add(new DivisionStep(51, 6, 48, 3));
        steps.add(new DivisionStep(32, 4, 32, 0));
        DivisionResult result = new DivisionResult(512, 8, steps);

        when(mathProvider.provideMathDivision(512, 8)).thenReturn(result);
        when(viewProvider.provideView(result)).thenReturn(expected);

        assertEquals(expected, calculator.calculateDivision(512, 8));
        verify(validator).validate(512, 8);
    }

    @Test
    void calculateDivisionShouldThrowExceptionForWrongArguments() {

        doThrow(IllegalArgumentException.class).when(validator).validate(-1, 2);

        assertThrows(IllegalArgumentException.class, () -> calculator.calculateDivision(-1, 2));
        verify(validator).validate(-1, 2);
        verifyNoInteractions(mathProvider);
        verifyNoInteractions(viewProvider);
    }
}
