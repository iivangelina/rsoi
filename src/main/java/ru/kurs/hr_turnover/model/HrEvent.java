package ru.kurs.hr_turnover.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "hr_events")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HrEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", length = 30, nullable = false)
    private HrEventType eventType;

    @Column(name = "event_date", nullable = false)
    private LocalDate eventDate;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "from_department_id")
    private Department fromDepartment;

    @ManyToOne
    @JoinColumn(name = "to_department_id")
    private Department toDepartment;

    @ManyToOne
    @JoinColumn(name = "from_position_id")
    private Position fromPosition;

    @ManyToOne
    @JoinColumn(name = "to_position_id")
    private Position toPosition;

    @ManyToOne
    @JoinColumn(name = "reason_id")
    private Reason reason;

    @Column(name = "comment", length = 500)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "created_by_user_id")
    private User createdBy;
}
