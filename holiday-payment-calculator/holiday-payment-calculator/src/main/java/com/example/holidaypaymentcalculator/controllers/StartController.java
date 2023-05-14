package com.example.holidaypaymentcalculator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class StartController {

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

    public void validateInput(String as, String vd) throws IOException {
        String message = "";
        Boolean valid = true;
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

    public double getHolidayPayment(double averageSalary, double vacationDuration) {
        double scale = Math.pow(10, 3);
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
//        if (as.isEmpty()) {
//            message += "Average salary not specified!\n";
//            valid = false;
//        }
//        if (vd.isEmpty()) {
//            message += "Vacation duration not specified!\n";
//            valid = false;
//        }
//        if (!as.isEmpty() && !vd.isEmpty()) {
//            try {
//                double averageSalary = Double.parseDouble(as);
//                double vacationDuration = Double.parseDouble(vd);
//                if (averageSalary <= 0 || vacationDuration <= 0) {
//                    message = "Average salary and Vacation duration must be above zero!\n";
//                    valid = false;
//                } else {
//                    double holidayPayment = getHolidayPayment(averageSalary, vacationDuration);
//                    message = "Your holiday payment is: " + holidayPayment;
//                }
//            } catch (Exception e) {
//                message = "Average salary and Vacation duration must be a number! ";
//            }
//        }
