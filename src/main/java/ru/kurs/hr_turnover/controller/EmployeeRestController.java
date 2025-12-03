package ru.kurs.hr_turnover.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kurs.hr_turnover.model.Employee;
import ru.kurs.hr_turnover.repo.EmployeeRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class EmployeeRestController {

    private final EmployeeRepository employeeRepository;

    @GetMapping("/api/employees/active")
    public List<SimpleEmployee> active() {
        return employeeRepository.findAll().stream()
                .filter(Employee::isActive)
                .map(e -> new SimpleEmployee(
                        e.getId(),
                        e.getLastName() + " " + e.getFirstName() +
                                (e.getMiddleName() != null ? " " + e.getMiddleName() : "")
                ))
                .toList();
    }

    public record SimpleEmployee(Long id, String name) {}
}
