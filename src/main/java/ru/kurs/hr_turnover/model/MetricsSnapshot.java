package ru.kurs.hr_turnover.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "metrics_snapshot")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MetricsSnapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "period_type", nullable = false, length = 10)
    private String periodType;   // MONTH / QUARTER / YEAR

    @Column(name = "period_value", nullable = false, length = 10)
    private String periodValue;  // 2025-10 и т.п.

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "turnover_m", precision = 7, scale = 4)
    private BigDecimal turnoverM;

    @Column(name = "turnover_y", precision = 7, scale = 4)
    private BigDecimal turnoverY;

    @Column(name = "retention_12m", precision = 7, scale = 4)
    private BigDecimal retention12m;

    @Column(name = "calculated_at", nullable = false)
    private LocalDateTime calculatedAt;
}

