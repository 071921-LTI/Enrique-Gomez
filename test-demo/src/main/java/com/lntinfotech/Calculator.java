package com.lntinfotech;

import com.lntinfotech.exceptions.CalculatorException;

public class Calculator {

    /* Should be able to:
    *   - add
    *   - subtract
    *   - multiply
    *   - divide
    *       - thorw a CalculatorException when attempting to divide by 0
    *   - isPrime: checks if a number is a prime number
    *   - compareThreeDecimals: returns true if the same up to 3 decimals
    *       - 3.123.compare...(3.123445) should return true
    */

    public double add(double x, double y) {
        return x + y;
    }

    public double subtract(double x, double y) {
        return x - y;
    }

    public double multiply(double x, double y) {
        return x * y;
    }

    public double divide(double x, double y) throws CalculatorException {
        if (y == 0) throw new CalculatorException("Can't divide by 0!");
        return x / y;
    }
}
