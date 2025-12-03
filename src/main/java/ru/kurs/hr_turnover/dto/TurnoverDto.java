package ru.kurs.hr_turnover.dto;

import java.math.BigDecimal;
import java.time.YearMonth;

public class TurnoverDto {

    private YearMonth period;

    private BigDecimal turnoverPercent;

    private long terminations;

    private long avgHeadcount;

    private Integer departmentId;
    private String departmentName;

    public TurnoverDto() {
    }

    public TurnoverDto(YearMonth period,
                       BigDecimal turnoverPercent,
                       long terminations,
                       long avgHeadcount,
                       Integer departmentId,
                       String departmentName) {
        this.period = period;
        this.turnoverPercent = turnoverPercent;
        this.terminations = terminations;
        this.avgHeadcount = avgHeadcount;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public YearMonth getPeriod() {
        return period;
    }

    public void setPeriod(YearMonth period) {
        this.period = period;
    }

    public BigDecimal getTurnoverPercent() {
        return turnoverPercent;
    }

    public void setTurnoverPercent(BigDecimal turnoverPercent) {
        this.turnoverPercent = turnoverPercent;
    }

    public long getTerminations() {
        return terminations;
    }

    public void setTerminations(long terminations) {
        this.terminations = terminations;
    }

    public long getAvgHeadcount() {
        return avgHeadcount;
    }

    public void setAvgHeadcount(long avgHeadcount) {
        this.avgHeadcount = avgHeadcount;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
