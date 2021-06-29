package ru.kiianov.foxminded.integerdivision.domain;

import java.util.List;
import java.util.Objects;

public class DivisionResult {
    private final List<DivisionStep> steps;
    private final int divisor;
    private final int dividend;

    public DivisionResult(int dividend, int divisor, List<DivisionStep> steps) {
        this.dividend = dividend;
        this.divisor = divisor;
        this.steps = steps;
    }

    public List<DivisionStep> getSteps() {
        return steps;
    }

    public int getDivisor() {
        return divisor;
    }

    public int getDividend() {
        return dividend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DivisionResult that = (DivisionResult) o;
        return divisor == that.divisor &&
                dividend == that.dividend &&
                Objects.equals(steps, that.steps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(steps, divisor, dividend);
    }

    @Override
    public String toString() {
        return "DivisionResult{\n" +
                "steps=" + steps +
                ", divisor=" + divisor + "\n" +
                ", dividend=" + dividend + "\n" +
                '}';
    }
}
