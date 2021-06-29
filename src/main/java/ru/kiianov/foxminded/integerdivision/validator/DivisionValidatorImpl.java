package ru.kiianov.foxminded.integerdivision.validator;

public class DivisionValidatorImpl implements DivisionValidator {

    @Override
    public void validate(int dividend, int divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("Divisor cannot be 0, division by zero.");
        }

        if (dividend < 0) {
            throw new IllegalArgumentException("Dividend cannot be negative");
        }

        if (divisor < 0) {
            throw new IllegalArgumentException("Divisor cannot be negative");
        }
    }
}
