package ru.kurs.hr_turnover.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.kurs.hr_turnover.model.Employee;

import java.time.LocalDate;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("""
           select e
           from Employee e
           where e.hireDate <= :date
             and e.active = true
           """)
    List<Employee> findActiveOn(LocalDate date);
}
