package ru.kurs.hr_turnover.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "alerts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "metric_code", nullable = false, length = 50)
    private String metricCode;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "period_type", nullable = false, length = 10)
    private String periodType;

    @Column(name = "period_value", nullable = false, length = 10)
    private String periodValue;

    @Column(name = "fact_value", precision = 7, scale = 4, nullable = false)
    private BigDecimal factValue;

    @Column(name = "threshold_value", precision = 7, scale = 4, nullable = false)
    private BigDecimal thresholdValue;

    @Column(name = "status", nullable = false, length = 10)
    private String status; // NEW / ACK

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "ack_at")
    private LocalDateTime ackAt;
}
