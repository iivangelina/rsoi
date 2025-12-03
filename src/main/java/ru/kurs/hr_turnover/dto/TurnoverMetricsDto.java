package ru.kurs.hr_turnover.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TurnoverMetricsDto {

    private long totalEmployees;

    private long terminations;

    private long hires;

    private BigDecimal turnoverPercent;
}
