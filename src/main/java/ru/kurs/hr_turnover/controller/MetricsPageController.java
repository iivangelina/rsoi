package ru.kurs.hr_turnover.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kurs.hr_turnover.service.MetricsService;

@Controller
@RequestMapping("/metrics")
@RequiredArgsConstructor
public class MetricsPageController {

    private static final Logger log = LoggerFactory.getLogger(MetricsPageController.class);
    private final MetricsService metricsService;

    @GetMapping({"", "/"})
    public String page(Model model) {
        log.info(">>> /metrics page called");
        model.addAttribute("m", metricsService.calcForCurrentMonth());
        return "metrics/overview";
    }
}
