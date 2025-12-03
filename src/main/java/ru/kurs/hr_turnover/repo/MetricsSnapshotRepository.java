package ru.kurs.hr_turnover.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kurs.hr_turnover.model.MetricsSnapshot;

import java.util.List;

public interface MetricsSnapshotRepository extends JpaRepository<MetricsSnapshot, Long> {

    MetricsSnapshot findFirstByPeriodTypeOrderByCalculatedAtDesc(String periodType);

    List<MetricsSnapshot> findByPeriodTypeOrderByCalculatedAtDesc(String periodType);
}
