package ru.kiianov.foxminded.integerdivision;

import ru.kiianov.foxminded.integerdivision.provider.DivisionMathProviderImpl;
import ru.kiianov.foxminded.integerdivision.provider.DivisionViewProviderImpl;
import ru.kiianov.foxminded.integerdivision.validator.DivisionValidatorImpl;

import java.util.Scanner;

public class IntegerDivisionConsoleApplication {
    public static void main(String[] args) {
        IntegerDivisionCalculator divisionCalculator = new IntegerDivisionCalculator
                (new DivisionMathProviderImpl(),
                        new DivisionValidatorImpl(),
                        new DivisionViewProviderImpl() {
                        });

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Please input the Dividend: ");
            int dividend = scanner.nextInt();
            System.out.println("Please input the Divisor: ");
            int divisor = scanner.nextInt();
            System.out.println();
            System.out.println(divisionCalculator.calculateDivision(dividend, divisor));
        }
    }
}
