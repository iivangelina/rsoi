package ru.kurs.hr_turnover.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kurs.hr_turnover.model.Reason;

public interface ReasonRepository extends JpaRepository<Reason, Long> {
}
