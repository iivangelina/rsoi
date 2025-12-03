package ru.kurs.hr_turnover.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reasons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reason {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String code;

    @Column(nullable = false, length = 255)
    private String name;

}
