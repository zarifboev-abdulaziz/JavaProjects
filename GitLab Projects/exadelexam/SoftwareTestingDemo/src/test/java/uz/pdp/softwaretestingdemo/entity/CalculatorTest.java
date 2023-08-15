package uz.pdp.softwaretestingdemo.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private final Calculator calculator = new Calculator();

    @Test
    void itShouldReturnSumOfTwoNumbers() {
        //given
        int a = 20;
        int b = 30;

        int expected = 50;

        assertEquals(expected, calculator.add(a, b));
        assertEquals(expected, calculator.add(3, 47));
    }
}