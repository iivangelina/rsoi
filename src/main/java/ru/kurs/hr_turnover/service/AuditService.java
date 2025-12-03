package ru.kurs.hr_turnover.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.kurs.hr_turnover.model.AuditLog;
import ru.kurs.hr_turnover.model.User;
import ru.kurs.hr_turnover.repo.AuditLogRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuditService {

    private final AuditLogRepository auditLogRepository;

    public void write(User user, String entity, String action, String details) {
        AuditLog log = AuditLog.builder()
                .ts(LocalDateTime.now())
                .user(user)
                .entity(entity)
                .action(action)
                .details(details)
                .build();
        auditLogRepository.save(log);
    }
}
