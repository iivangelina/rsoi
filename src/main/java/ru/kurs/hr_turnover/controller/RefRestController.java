package ru.kurs.hr_turnover.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.kurs.hr_turnover.model.Department;
import ru.kurs.hr_turnover.model.Employee;
import ru.kurs.hr_turnover.model.Position;
import ru.kurs.hr_turnover.model.Reason;
import ru.kurs.hr_turnover.repo.DepartmentRepository;
import ru.kurs.hr_turnover.repo.EmployeeRepository;
import ru.kurs.hr_turnover.repo.PositionRepository;
import ru.kurs.hr_turnover.repo.ReasonRepository;

import java.util.List;

@RestController
@RequestMapping("/api/refs")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class RefRestController {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;
    private final ReasonRepository reasonRepository;

    @GetMapping("/employees/active")
    public List<Employee> activeEmployees() {
        return employeeRepository.findAll()
                .stream()
                .filter(Employee::isActive)
                .toList();
    }

    @GetMapping("/departments")
    public List<Department> departments() {
        return departmentRepository.findAll();
    }

    @GetMapping("/positions")
    public List<Position> positions() {
        return positionRepository.findAll();
    }

    @GetMapping("/reasons")
    public List<Reason> reasons() {
        return reasonRepository.findAll();
    }
}
