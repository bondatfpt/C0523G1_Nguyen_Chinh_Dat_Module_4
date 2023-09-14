package com.codegym.controller;

import com.codegym.service.CurrencyConversion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CurrencyConversionController {
    @Autowired
    private CurrencyConversion currencyConversion;

    @GetMapping("/currency-conversion")
    public String showFormCovert() {
        return "currency-conversion";
    }
    @PostMapping("/currency-conversion/vnd")
    public String showResultVnd(@RequestParam String vnd, Model model) {
        double resultVnd = currencyConversion.convertCurrencyVndToUsd(vnd);
        model.addAttribute("vnd", vnd);
        model.addAttribute("resultVnd", resultVnd);
        return "currency-conversion";
    }

    @PostMapping("/currency-conversion/usd")
    public String showResultUsd(@RequestParam String usd, Model model) {
        double resultUsd = currencyConversion.convertCurrencyUsdToVnd(usd);
        model.addAttribute("usd",usd );
        model.addAttribute("resultUsd", resultUsd);
        return "currency-conversion";
    }
}
