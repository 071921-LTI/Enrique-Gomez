package com.lntinfotech;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.lntinfotech.exceptions.CalculatorException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
public class CalculatorTest {
    /*
    * JUnit annotations
    *   - @BeforeEach
    *   - @AfterEach
    *   - @BeforeClass
    *   - @AfterClass
    *   - @Test
    *   - @Ignore
    *   - @Order
    */

    public static Calculator calc;

    @BeforeAll
    public static void setUp() {
        calc = new Calculator();
        System.out.println("In beforeAll");
    }

    @AfterAll
    public static void tearDown() {
        System.out.println("In afterAll");
    }

    @Order(1)
    @Test
    public void addTwoAndTwo() {
        double expected = 4;
        double actualResult = calc.add(2, 2);
        assertEquals(expected, actualResult, "Adding 2 and 2 should be 4");
    }

    @Order(2)
    @Test
    public void add0And0() {
        double expected = 0;
        double actualResult = calc.add(0, 0);
        assertEquals(expected, actualResult, "Adding 0 and 0 should be 0");
    }

    @Order(3)
    @Test
    public void add1And1() {
        double expected = 2;
        double actualResult = calc.add(1, 1);
        assertEquals(expected, actualResult, "Adding 1 and 1 should be 2");
    }

    @Order(4)
    @Test
    public void add0And1() {
        double expected = 1;
        double actualResult = calc.add(0, 1);
        assertEquals(expected, actualResult, "Adding 1 and 0 should be 1");
    }

    @Order(5)
    @Test
    public void add5And6() {
        double expected = 11;
        double actualResult = calc.add(5, 6);
        assertEquals(expected, actualResult, "Adding 5 and 6 should be 11");
    }

    @Order(6)
    @Test
    public void subtractTwoAndTwo() {
        double expected = 0;
        double actualResult = calc.subtract(2, 2);
        assertEquals(expected, actualResult, "Subtracting 2 and 2 should be 0");
    }

    @Order(7)
    @Test
    public void subtract1And0() {
        double expected = 1;
        double actualResult = calc.subtract(1, 0);
        assertEquals(expected, actualResult, "Subtracting 1 and 0 should be 1");
    }

    @Order(8)
    @Test
    public void multiplyBy0() {
        double expected = 0;
        double actualResult = calc.multiply(1, 0);
        assertEquals(expected, actualResult, "Multiplying 1 and 0 should be 0");
    }
    

    @Order(9)
    @Test
    public void divideBy0() {
        assertThrows(CalculatorException.class, () -> calc.divide(1, 0));
    }

    @Order(10)
    @Test
    public void is2Prime() {
        boolean expected = true;
        boolean actualResult = calc.isPrime(2);
        assertEquals(expected, actualResult, "2 is prime");
    }

    @Order(11)
    @Test
    public void is9Prime() {
        boolean expected = false;
        boolean actualResult = calc.isPrime(9);
        assertEquals(expected, actualResult, "9 is not prime");
    }

    @Order(12)
    @Test
    public void is1Prime() {
        boolean expected = true;
        boolean actualResult = calc.isPrime(1);
        assertEquals(expected, actualResult);
    }

    @Order(13)
    @Test
    public void is5Prime() {
        boolean expected = true;
        boolean actualResult = calc.isPrime(5);
        assertEquals(expected, actualResult);
    }

    @Order(14)
    @Test
    public void is100Prime() {
        boolean expected = false;
        boolean actualResult = calc.isPrime(100);
        assertEquals(expected, actualResult);
    }

    @Order(15)
    @Test
    public void hasThreeDecimals() {
        boolean expected = true;
        boolean actualResult = calc.compareThreeDecimals(3.123, 3.123445);
        assertEquals(expected, actualResult);
    }

    @Order(16)
    @Test
    public void doesNotHaveThreeDecimals() {
        boolean expected = false;
        boolean actualResult = calc.compareThreeDecimals(3.123, 3.12256);
        assertEquals(expected, actualResult);
    }
}
