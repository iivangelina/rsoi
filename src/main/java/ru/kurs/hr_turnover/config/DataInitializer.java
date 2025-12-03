package ru.kurs.hr_turnover.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kurs.hr_turnover.model.User;
import ru.kurs.hr_turnover.repo.UserRepository;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initUsers() {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setFullName("Системный администратор");
                admin.setPasswordHash(passwordEncoder.encode("admin123"));
                admin.setEnabled(1);
                admin.setActive(1);
                admin.setRole("ADMIN");
                userRepository.save(admin);
            }

            if (userRepository.findByUsername("user").isEmpty()) {
                User u = new User();
                u.setUsername("user");
                u.setFullName("Обычный пользователь");
                u.setPasswordHash(passwordEncoder.encode("user123"));
                u.setEnabled(1);
                u.setActive(1);
                u.setRole("USER");
                userRepository.save(u);
            }
        };
    }
}
