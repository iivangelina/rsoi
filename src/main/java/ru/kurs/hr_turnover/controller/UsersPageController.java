package ru.kurs.hr_turnover.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kurs.hr_turnover.repo.UserRepository;

@Controller
@RequiredArgsConstructor
public class UsersPageController {

    private final UserRepository userRepository;

    @GetMapping("/users")
    public String usersPage(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users/list";
    }
}
