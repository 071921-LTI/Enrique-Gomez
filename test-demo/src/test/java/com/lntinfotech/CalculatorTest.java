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
}
