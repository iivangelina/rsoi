package ru.kurs.hr_turnover.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.kurs.hr_turnover.service.UserService;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminUserController {

    private final UserService userService;

    @PostMapping("/admin/users/create")
    public String createUserFromAdmin(
            @RequestParam String username,
            @RequestParam String fullName,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            @RequestParam(required = false, defaultValue = "USER") String role,
            RedirectAttributes ra
    ) {
        if (!password.equals(confirmPassword)) {
            ra.addFlashAttribute("errorMsg", "Пароли не совпадают");
            return "redirect:/admin/dashboard";
        }

        try {

            userService.createUser(username, password, fullName, role);
            ra.addFlashAttribute("successMsg", "Пользователь «" + username + "» создан");
        } catch (IllegalArgumentException ex) {
            ra.addFlashAttribute("errorMsg", ex.getMessage());
        }

        return "redirect:/admin/dashboard";
    }

    @PostMapping("/admin/users/update")
    public String updateUserFromAdmin(
            @RequestParam Long id,
            @RequestParam String username,
            @RequestParam String fullName,
            @RequestParam(required = false, defaultValue = "USER") String role,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) String confirmPassword,
            @RequestParam(required = false, defaultValue = "1") int enabled,
            @RequestParam(required = false, defaultValue = "1") int active,
            RedirectAttributes ra
    ) {

        if (password != null && !password.isBlank()) {
            if (!password.equals(confirmPassword)) {
                ra.addFlashAttribute("errorMsg", "Пароли не совпадают");
                return "redirect:/admin/dashboard";
            }
        }

        try {
            userService.updateUser(
                    id,
                    username,
                    fullName,
                    role,
                    enabled,
                    active,
                    (password != null && !password.isBlank()) ? password : null
            );
            ra.addFlashAttribute("successMsg", "Пользователь обновлён");
        } catch (IllegalArgumentException ex) {
            ra.addFlashAttribute("errorMsg", ex.getMessage());
        }

        return "redirect:/admin/dashboard";
    }

    // 3) УДАЛЕНИЕ
    @PostMapping("/admin/users/delete")
    public String deleteUserFromAdmin(
            @RequestParam Long id,
            RedirectAttributes ra
    ) {
        try {
            userService.deleteUser(id);
            ra.addFlashAttribute("successMsg", "Пользователь удалён");
        } catch (IllegalArgumentException ex) {
            ra.addFlashAttribute("errorMsg", ex.getMessage());
        }
        return "redirect:/admin/dashboard";
    }
}
