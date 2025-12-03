package ru.kurs.hr_turnover.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@PreAuthorize("hasAnyRole('USER','ADMIN')")
public class UserPanelController {

    @GetMapping("/user/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("title", "Рабочий стол сотрудника (HR)");
        return "user-dashboard";
    }
}
