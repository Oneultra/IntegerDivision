package org.task.pavel;

import org.task.pavel.provider.DivisionMathProvider;
import org.task.pavel.provider.DivisionViewProvider;
import org.task.pavel.provider.DivisionMathProviderImpl;
import org.task.pavel.provider.DivisionViewProviderImpl;
import org.task.pavel.validator.Validator;
import org.task.pavel.validator.ValidatorImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IntegerDivisionConsoleApplication {
    public static void main(String[] args) {

        Validator validator = new ValidatorImpl();
        DivisionMathProvider divisionMathProvider = new DivisionMathProviderImpl();
        DivisionViewProvider divisionViewProvider = new DivisionViewProviderImpl();
        DivisionCalculator divisionCalculator =  new DivisionCalculator(validator,
                divisionMathProvider, divisionViewProvider );

        try (BufferedReader inputDigits = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("Insert dividend: ");
            int dividend = Integer.parseInt(inputDigits.readLine());
            System.out.println("Insert divisor: ");
            int divisor = Integer.parseInt(inputDigits.readLine());
            System.out.println(divisionCalculator.calculate(dividend, divisor));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
