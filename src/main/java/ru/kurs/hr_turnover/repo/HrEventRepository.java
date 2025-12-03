package ru.kurs.hr_turnover.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kurs.hr_turnover.model.HrEvent;

import java.time.LocalDate;
import java.util.List;

public interface HrEventRepository extends JpaRepository<HrEvent, Long> {

    List<HrEvent> findByEventDateBetween(LocalDate start, LocalDate end);

    @Query("select e from HrEvent e where e.eventDate between :start and :end and e.eventType = 'TERMINATION'")
    List<HrEvent> findTerminations(LocalDate start, LocalDate end);

    @Query("select e from HrEvent e where e.eventDate between :start and :end and e.eventType = 'HIRE'")
    List<HrEvent> findHires(LocalDate start, LocalDate end);
}
