package com.hamza.studentcourse.validation;

public class PasswordValidator {

    public static boolean isStrong(String password) {

        return password.matches(
                "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$"
        );
    }
}