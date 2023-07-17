package com.example.holidaypaymentcalculator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.IOException;

@Controller
public class StartController {
// test for repository
    @GetMapping("/start")
    public String start() {
        return "start";
    }


    @GetMapping("/calculate")
    public String calculate(@RequestParam(value = "averageSalary", required = false) String as,
                            @RequestParam(value = "vacationDuration", required = false) String vd,
                            Model model) {
        String message;
        try {
            validateInput(as, vd);
            double holidayPayment = getHolidayPayment(Double.parseDouble(as), Double.parseDouble(vd));
            message = "Your holiday payment is: " + holidayPayment;
        } catch (IOException e) {
            message = e.getMessage();
        }
        model.addAttribute("message", message);
        return "start";
    }

    public static void validateInput(String as, String vd) throws IOException {
        String message = "";
        boolean valid = true;
        if (!isPositiveNumber(as)) {
            message += "Please specify average salary as number greater then zero.\n";
            valid = false;
        }
        if (!isPositiveNumber(vd)) {
            message += "Please specify duration vacation as number greater then zero.\n";
            valid = false;
        }
        if (!valid) {
            throw new IOException(message);
        }
    }

    public static double getHolidayPayment(double averageSalary, double vacationDuration) {
        double scale = Math.pow(10, 2);
        return Math.ceil(averageSalary * vacationDuration * scale)/scale;
    }

    public static boolean isPositiveNumber(String str) {
        if (str.isEmpty()) {
            return false;
        }
        try {
            double d = Double.parseDouble(str);
            if (d <= 0) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}

