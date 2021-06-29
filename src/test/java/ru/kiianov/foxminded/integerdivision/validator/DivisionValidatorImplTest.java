package ru.kiianov.foxminded.integerdivision.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DivisionValidatorImplTest {
    private final DivisionValidator divisionValidator = new DivisionValidatorImpl();

    @Test
    void validateShouldThrowExceptionIfDivisorIsZero() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                divisionValidator.validate(512, 0));
        assertEquals("Divisor cannot be 0, division by zero.", exception.getMessage());
    }

    @Test
    void validateShouldThrowExceptionForNegativeDividend() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                divisionValidator.validate(-2, 4));
        assertEquals("Dividend cannot be negative", exception.getMessage());
    }

    @Test
    void validateShouldThrowExceptionForNegativeDivisor() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                divisionValidator.validate(2, -4));
        assertEquals("Divisor cannot be negative", exception.getMessage());
    }

    @Test
    void validateShouldAcceptCorrectNumbers() {
        Assertions.assertDoesNotThrow(() -> divisionValidator.validate(512, 8));
    }
}
