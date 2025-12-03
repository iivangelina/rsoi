package ru.kurs.hr_turnover.dto;

import lombok.Data;

@Data
public class RegisterForm {
    private String username;
    private String fullName;
    private String password;
    private String confirmPassword;
}
