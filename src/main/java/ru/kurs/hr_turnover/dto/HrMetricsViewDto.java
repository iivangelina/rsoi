package ru.kurs.hr_turnover.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HrMetricsViewDto {
    private double turnoverMonth;
    private double turnoverYear;
    private double retention12m;
}
