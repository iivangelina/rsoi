package ru.kurs.hr_turnover.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.kurs.hr_turnover.model.User;
import ru.kurs.hr_turnover.repo.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class UserAdminController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public List<User> all() {
        return userRepository.findAll();
    }

    @PostMapping
    public User create(@RequestBody AdminCreateUserDto dto) {
        User u = new User();
        u.setUsername(dto.username());
        u.setFullName(dto.fullName());
        u.setPasswordHash(passwordEncoder.encode(dto.password()));
        u.setEnabled(1);
        u.setActive(1);
        u.setRole(dto.role());
        return userRepository.save(u);
    }

    public record AdminCreateUserDto(
            String username,
            String fullName,
            String password,
            String role
    ) {}
}
