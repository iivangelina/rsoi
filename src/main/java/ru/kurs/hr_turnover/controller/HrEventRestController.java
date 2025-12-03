package ru.kurs.hr_turnover.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.kurs.hr_turnover.model.*;
import ru.kurs.hr_turnover.repo.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/hr-events")
@RequiredArgsConstructor
public class HrEventRestController {

    private final HrEventRepository hrEventRepository;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;
    private final ReasonRepository reasonRepository;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<HrEvent> all() {
        return hrEventRepository.findAll();
    }


    @PostMapping(consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public ResponseEntity<?> createFromForm(
            @RequestParam("employeeId") Long employeeId,
            @RequestParam("eventType") String eventType,
            @RequestParam(value = "eventDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate eventDate,
            @RequestParam(value = "fromDepartmentId", required = false) Integer fromDepartmentId,
            @RequestParam(value = "toDepartmentId", required = false) Integer toDepartmentId,
            @RequestParam(value = "fromPositionId", required = false) Integer fromPositionId,
            @RequestParam(value = "toPositionId", required = false) Integer toPositionId,
            @RequestParam(value = "reasonId", required = false) Long reasonId,
            @RequestParam(value = "comment", required = false) String comment
    ) {
        Employee emp = employeeRepository.findById(employeeId).orElse(null);
        if (emp == null) {
            return ResponseEntity.badRequest().body("Сотрудник не найден: " + employeeId);
        }

        HrEventType type;
        try {
            type = HrEventType.valueOf(eventType.trim().toUpperCase());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("eventType должен быть HIRE | TRANSFER | TERMINATION");
        }

        HrEvent ev = new HrEvent();
        ev.setEmployee(emp);
        ev.setEventType(type);
        ev.setEventDate(eventDate != null ? eventDate : LocalDate.now());
        ev.setComment(comment);

        if (fromDepartmentId != null) {
            departmentRepository.findById(fromDepartmentId.longValue()).ifPresent(ev::setFromDepartment);
        }
        if (toDepartmentId != null) {
            departmentRepository.findById(toDepartmentId.longValue()).ifPresent(ev::setToDepartment);
        }
        if (fromPositionId != null) {
            positionRepository.findById(fromPositionId.longValue()).ifPresent(ev::setFromPosition);
        }
        if (toPositionId != null) {
            positionRepository.findById(toPositionId.longValue()).ifPresent(ev::setToPosition);
        }
        if (reasonId != null) {
            reasonRepository.findById(reasonId).ifPresent(ev::setReason);
        }

        if (type == HrEventType.TERMINATION) {
            emp.setActive(false);
            employeeRepository.save(emp);
        } else if (type == HrEventType.HIRE) {
            emp.setActive(true);
            employeeRepository.save(emp);
        }

        HrEvent saved = hrEventRepository.save(ev);
        return ResponseEntity.ok(saved);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public ResponseEntity<?> createFromJson(@RequestBody HrEvent dto) {

        if (dto.getEmployee() == null || dto.getEmployee().getId() == null) {
            return ResponseEntity.badRequest().body("employee.id обязателен");
        }

        Employee emp = employeeRepository.findById(dto.getEmployee().getId()).orElse(null);
        if (emp == null) {
            return ResponseEntity.badRequest().body("Сотрудник не найден: " + dto.getEmployee().getId());
        }

        if (dto.getEventType() == null) {
            return ResponseEntity.badRequest().body("eventType обязателен");
        }

        HrEvent ev = new HrEvent();
        ev.setEmployee(emp);
        ev.setEventType(dto.getEventType()); // Enum уже распаршен Jackson'ом из строки
        ev.setEventDate(dto.getEventDate() != null ? dto.getEventDate() : LocalDate.now());
        ev.setComment(dto.getComment());

        if (dto.getFromDepartment() != null && dto.getFromDepartment().getId() != null) {
            departmentRepository.findById(dto.getFromDepartment().getId()).ifPresent(ev::setFromDepartment);
        }
        if (dto.getToDepartment() != null && dto.getToDepartment().getId() != null) {
            departmentRepository.findById(dto.getToDepartment().getId()).ifPresent(ev::setToDepartment);
        }
        if (dto.getFromPosition() != null && dto.getFromPosition().getId() != null) {
            positionRepository.findById(dto.getFromPosition().getId()).ifPresent(ev::setFromPosition);
        }
        if (dto.getToPosition() != null && dto.getToPosition().getId() != null) {
            positionRepository.findById(dto.getToPosition().getId()).ifPresent(ev::setToPosition);
        }
        if (dto.getReason() != null && dto.getReason().getId() != null) {
            reasonRepository.findById(dto.getReason().getId()).ifPresent(ev::setReason);
        }

        if (dto.getEventType() == HrEventType.TERMINATION) {
            emp.setActive(false);
            employeeRepository.save(emp);
        } else if (dto.getEventType() == HrEventType.HIRE) {
            emp.setActive(true);
            employeeRepository.save(emp);
        }

        HrEvent saved = hrEventRepository.save(ev);
        return ResponseEntity.ok(saved);
    }
}
