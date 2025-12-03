package ru.kurs.hr_turnover.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.kurs.hr_turnover.dto.HrMetricsViewDto;
import ru.kurs.hr_turnover.model.Employee;
import ru.kurs.hr_turnover.model.HrEvent;
import ru.kurs.hr_turnover.repo.EmployeeRepository;
import ru.kurs.hr_turnover.repo.HrEventRepository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class MetricsServiceImpl implements MetricsService {

    private final HrEventRepository hrEventRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public HrMetricsViewDto calcForCurrentMonth() {
        LocalDate now = LocalDate.now();
        return calcForMonth(now.getYear(), now.getMonthValue());
    }

    @Override
    public HrMetricsViewDto calcForMonth(int year, int month) {
        YearMonth ym = YearMonth.of(year, month);
        LocalDate start = ym.atDay(1);
        LocalDate end = ym.atEndOfMonth();

        List<HrEvent> termsMonth = hrEventRepository.findTerminations(start, end);
        int termCountMonth = termsMonth.size();
        double avgHeadcountMonth = (headcountOn(start) + headcountOn(end)) / 2.0;
        double turnoverM = avgHeadcountMonth > 0 ? termCountMonth / avgHeadcountMonth * 100.0 : 0.0;

        YearMonth ym12 = ym.minusMonths(11);
        LocalDate yearStart = ym12.atDay(1);

        List<Employee> cohort = employeeRepository.findAll().stream()
                .filter(e -> !e.getHireDate().isAfter(yearStart))
                .toList();
        int cohortSize = cohort.size();
        Set<Long> cohortIds = cohort.stream().map(Employee::getId).collect(Collectors.toSet());

        List<HrEvent> termsYear = hrEventRepository.findTerminations(yearStart, end);

        long cohortTerms = termsYear.stream()
                .filter(ev -> ev.getEmployee() != null && cohortIds.contains(ev.getEmployee().getId()))
                .count();

        double hcYearStart = headcountOn(yearStart);
        double hcYearEnd = headcountOn(end);
        double avgHcYear = (hcYearStart + hcYearEnd) / 2.0;
        double turnoverY = avgHcYear > 0 ? termsYear.size() / avgHcYear * 100.0 : 0.0;

        double retention12;
        if (cohortSize == 0) {
            retention12 = 100.0;
        } else {
            retention12 = (cohortSize - cohortTerms) * 100.0 / cohortSize;
        }

        return new HrMetricsViewDto(
                round(turnoverM),
                round(turnoverY),
                round(retention12)
        );
    }

    private int headcountOn(LocalDate date) {
        var hired = employeeRepository.findAll().stream()
                .filter(e -> !e.getHireDate().isAfter(date))
                .toList();

        var termsBefore = hrEventRepository.findTerminations(LocalDate.of(1900, 1, 1), date);
        var terminatedIds = termsBefore.stream()
                .map(ev -> ev.getEmployee().getId())
                .collect(Collectors.toSet());

        return (int) hired.stream()
                .filter(e -> !terminatedIds.contains(e.getId()))
                .count();
    }

    private double round(double v) {
        return Math.round(v * 100.0) / 100.0;
    }
}
