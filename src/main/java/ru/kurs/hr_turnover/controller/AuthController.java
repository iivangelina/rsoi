package ru.kurs.hr_turnover.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kurs.hr_turnover.dto.RegisterForm;
import ru.kurs.hr_turnover.model.User;
import ru.kurs.hr_turnover.repo.UserRepository;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String doRegister(
            @ModelAttribute("userForm") RegisterForm form,
            RedirectAttributes ra
    ) {

        if (form.getPassword() == null || !form.getPassword().equals(form.getConfirmPassword())) {
            ra.addFlashAttribute("error", "Пароль и подтверждение не совпадают");
            return "redirect:/register";
        }

        if (userRepository.findByUsername(form.getUsername()).isPresent()) {
            ra.addFlashAttribute("error", "Пользователь с таким именем уже существует");
            return "redirect:/register";
        }

        User user = new User();
        user.setUsername(form.getUsername());
        user.setFullName(form.getFullName() != null && !form.getFullName().isBlank()
                ? form.getFullName()
                : form.getUsername());
        user.setPasswordHash(passwordEncoder.encode(form.getPassword()));
        user.setEnabled(1);
        user.setActive(1);
        user.setRole("USER");


        userRepository.save(user);

        ra.addFlashAttribute("regSuccess", "Регистрация прошла, войдите в систему");
        return "redirect:/login";
    }
}
