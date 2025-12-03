package ru.kurs.hr_turnover.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kurs.hr_turnover.model.User;
import ru.kurs.hr_turnover.repo.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public User registerUser(String username, String rawPassword, String fullName) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Пользователь с таким логином уже существует");
        }

        User u = new User();
        u.setUsername(username);
        u.setPasswordHash(passwordEncoder.encode(rawPassword));
        u.setFullName(fullName);
        u.setRole("USER");
        u.setEnabled(1);
        u.setActive(1);

        return userRepository.save(u);
    }


    @Transactional
    public User createUser(String username, String rawPassword, String fullName, String role) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Пользователь с таким логином уже существует");
        }

        User u = new User();
        u.setUsername(username);
        u.setPasswordHash(passwordEncoder.encode(rawPassword));
        u.setFullName(fullName);
        u.setRole(role);       // "ADMIN" или "USER"
        u.setEnabled(1);
        u.setActive(1);

        return userRepository.save(u);
    }

    @Transactional
    public User updateUser(Long id,
                           String username,
                           String fullName,
                           String role,
                           int enabled,
                           int active,
                           String newRawPassword) {
        User u = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));

        u.setUsername(username);
        u.setFullName(fullName);
        u.setRole(role);
        u.setEnabled(enabled);
        u.setActive(active);

        if (newRawPassword != null && !newRawPassword.isBlank()) {
            u.setPasswordHash(passwordEncoder.encode(newRawPassword));
        }

        return userRepository.save(u);
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("Пользователь не найден");
        }
        userRepository.deleteById(id);
    }
}
