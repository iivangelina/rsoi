package ru.kurs.hr_turnover.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "alert_thresholds")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertThreshold {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "metric_code", nullable = false, length = 50)
    private String metricCode;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "direction", nullable = false, length = 10)
    private String direction;

    @Column(name = "threshold_value", precision = 7, scale = 4, nullable = false)
    private BigDecimal thresholdValue;
}
