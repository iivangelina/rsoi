package ru.kurs.hr_turnover.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kurs.hr_turnover.repo.DepartmentRepository;
import ru.kurs.hr_turnover.repo.PositionRepository;
import ru.kurs.hr_turnover.repo.ReasonRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN','USER')")
public class LookupRestController {

    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;
    private final ReasonRepository reasonRepository;

    @GetMapping("/api/departments")
    public List<IdName> departments() {
        return departmentRepository.findAll().stream()
                .map(d -> new IdName(d.getId(), d.getName()))
                .toList();
    }

    @GetMapping("/api/positions")
    public List<IdName> positions() {
        return positionRepository.findAll().stream()
                .map(p -> new IdName(p.getId(), p.getName()))
                .toList();
    }

    @GetMapping("/api/reasons")
    public List<IdName> reasons() {
        return reasonRepository.findAll().stream()
                .map(r -> new IdName(r.getId(), r.getName()))
                .toList();
    }

    public record IdName(Long id, String name) {}
}
