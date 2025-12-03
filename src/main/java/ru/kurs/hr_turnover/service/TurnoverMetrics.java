package ru.kurs.hr_turnover.service;

public record TurnoverMetrics(
        long terminations,
        long hires,
        double turnoverPercent,
        String period
) {}
