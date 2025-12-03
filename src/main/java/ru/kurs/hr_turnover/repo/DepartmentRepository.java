package ru.kurs.hr_turnover.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kurs.hr_turnover.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
