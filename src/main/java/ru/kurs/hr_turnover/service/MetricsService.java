package ru.kurs.hr_turnover.service;

import ru.kurs.hr_turnover.dto.HrMetricsViewDto;

public interface MetricsService {
    HrMetricsViewDto calcForMonth(int year, int month);
    HrMetricsViewDto calcForCurrentMonth();
}
