package ru.kurs.hr_turnover.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kurs.hr_turnover.dto.HrMetricsViewDto;
import ru.kurs.hr_turnover.service.MetricsService;

@RestController
@RequestMapping("/api/metrics")
@RequiredArgsConstructor
public class MetricsRestController {

    private final MetricsService metricsService;

    @GetMapping("/current")
    public HrMetricsViewDto current() {
        return metricsService.calcForCurrentMonth();
    }
}
