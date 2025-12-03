package ru.kurs.hr_turnover.service.dto;

import lombok.Data;

@Data
public class MetricsDto {
    private String period;
    private long headcount;     // текущая численность
    private long hires;         // наймы за месяц
    private long terms;         // увольнения за месяц
    private double turnover;    // текучесть % за месяц
}
