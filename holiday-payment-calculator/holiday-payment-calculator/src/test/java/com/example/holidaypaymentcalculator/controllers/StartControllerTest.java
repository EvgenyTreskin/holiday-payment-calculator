package com.example.holidaypaymentcalculator.controllers;


import org.junit.Ignore;
import org.junit.jupiter.api.Test;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class StartControllerTest {
    private final StartController startController = new StartController();
    String invalidSalaryMassage = "Please specify average salary as number greater then zero.\n";
    String invalidDurationMessage = "Please specify duration vacation as number greater then zero.\n";


    @Test
    void startTest() {
        assertEquals("start", startController.start());
    }


    @Test
    void validateInputTrowsExceptionTest() {
        Exception exception = assertThrows(IOException.class, () -> startController.validateInput("", ""));
        assertTrue(exception.getMessage().contains(invalidSalaryMassage));
        assertTrue(exception.getMessage().contains(invalidDurationMessage));
    }
    @Test
    void validateInputNotTrowsExceptionTest() {
        assertDoesNotThrow(() -> startController.validateInput("4000", "3.5"));
    }


    @Test
    void getHolidayPaymentTwoPositiveTest() {
        assertEquals(14, startController.getHolidayPayment(
                2.8, 5));
    }

    @Test
    void getHolidayPaymentTwoNegativeTest() {
        assertEquals(15, startController.getHolidayPayment(
                -6, -2.5));
    }

    @Test
    void getHolidayPaymentTwoZeroTest() {
        assertEquals(0, startController.getHolidayPayment(
                0, 0));
    }

    @Test
    void getHolidayPaymentPositiveNegativeTest() {
        assertEquals(-749.28, startController.getHolidayPayment(
                33.6, -22.3));
    }

    @Test
    void getHolidayPaymentZeroNegativeTest() {
        assertEquals(-0.0, startController.getHolidayPayment(
                0, -100));
    }

    @Test
    void getHolidayPaymentZeroPositiveTest() {
        assertEquals(0, startController.getHolidayPayment(
                63.2, 0));
    }

    @Test
    void isPositiveNumberTestPositive() {
        assertTrue(startController.isPositiveNumber("30.3"));
    }

    @Test
    void isPositiveNumberTestNegative() {
        assertFalse(startController.isPositiveNumber("-2"));
    }

    @Test
    void isPositiveNumberTestZero() {
        assertFalse(startController.isPositiveNumber("0"));
    }

    @Test
    void isPositiveNumberTestChar() {
        assertFalse(startController.isPositiveNumber("h"));
    }

}