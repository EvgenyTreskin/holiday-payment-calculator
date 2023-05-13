package com.example.holidaypaymentcalculator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class StartController {

    @GetMapping("/start")
    public String start(HttpServletRequest request, Model model) {
        return "start";
    }


    @GetMapping("/calculate")
    public String calculate(@RequestParam(value = "averageSalary", required = false) String as,
                            @RequestParam(value = "vacationDuration", required = false) String vd,
                            Model model) {

        String message = "";
        if (as.isEmpty()) {
            message += "Average salary not specified! ";
        }
        if (vd.isEmpty()) {
            message += "Vacation duration not specified! ";
        }

        if (!vd.isEmpty() && !as.isEmpty()) {
            double averageSalary = Double.parseDouble(as);
            double vacationDuration = Double.parseDouble(vd);
            if (averageSalary <= 0 || vacationDuration <= 0) {
                message = "Average salary and Vacation duration must be above zero! ";
            } else {
                double holidayPayment = (averageSalary) * (vacationDuration);
                message = "Your holiday payment is: " + holidayPayment;
            }

        }

        model.addAttribute("message", message);
        return "start";
    }


}
