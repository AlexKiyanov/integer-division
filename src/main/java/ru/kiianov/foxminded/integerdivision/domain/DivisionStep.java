package ru.kiianov.foxminded.integerdivision.domain;

import java.util.Objects;

public class DivisionStep {
    private final int reminder;
    private final int quotient;
    private final int multiplier;
    private final int mod;

    public DivisionStep(int reminder, int quotient, int multiplier, int mod) {
        this.reminder = reminder;
        this.quotient = quotient;
        this.multiplier = multiplier;
        this.mod = mod;
    }

    public int getMod() {
        return mod;
    }

    public int getReminder() {
        return reminder;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public int getQuotient() {
        return quotient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DivisionStep that = (DivisionStep) o;
        return reminder == that.reminder &&
                quotient == that.quotient &&
                multiplier == that.multiplier &&
                mod == that.mod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(reminder, quotient, mod);
    }

    @Override
    public String toString() {
        return "DivisionStep{" +
                "reminder=" + reminder +
                ", multiplier=" + multiplier +
                ", quotient=" + quotient +
                ", mod=" + mod +
                "}\n";
    }
}
