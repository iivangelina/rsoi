package ru.kurs.hr_turnover.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kurs.hr_turnover.model.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
