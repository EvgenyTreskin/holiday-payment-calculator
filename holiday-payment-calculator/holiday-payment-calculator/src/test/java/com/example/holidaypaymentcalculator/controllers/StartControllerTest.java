package com.example.holidaypaymentcalculator.controllers;

import org.assertj.core.util.VisibleForTesting;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ui.Model;

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
    void validateInputTrowsNullTest() {
        Exception exception = assertThrows(IOException.class, () -> startController.validateInput("", ""));
        assertTrue(exception.getMessage().contains(invalidSalaryMassage));
        assertTrue(exception.getMessage().contains(invalidDurationMessage));
    }
    @Test
    void validateInputTrowsCharsTest() {
        Exception exception = assertThrows(IOException.class, () -> startController.validateInput("f", "/"));
        assertTrue(exception.getMessage().contains(invalidSalaryMassage));
        assertTrue(exception.getMessage().contains(invalidDurationMessage));
    }
    @Test
    void validateInputTrowsNegativeTest() {
        Exception exception = assertThrows(IOException.class, () -> startController.validateInput("-1", "-22.2"));
        assertTrue(exception.getMessage().contains(invalidSalaryMassage));
        assertTrue(exception.getMessage().contains(invalidDurationMessage));
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

}