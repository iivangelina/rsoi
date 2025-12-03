package ru.kurs.hr_turnover.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DictPageController {

    @GetMapping("/dict/ping")
    public String dictPingPage(Model model) {

        model.addAttribute("pingUrl", "/api/dict/ping");
        return "dict/ping";
    }

    @GetMapping("/dict/json")
    public String dictJsonPage(Model model) {
        model.addAttribute("jsonUrl", "/api/dict/ping");
        return "dict/json";
    }
}
