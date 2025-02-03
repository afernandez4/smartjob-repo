package com.smartjob.users.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    @Value("${password.regex}")
    private String passwordRegex;

    @Value("${password.message}")
    private String message;

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null || passwordRegex == null) {
            return false;
        }
        boolean matches = password.matches(passwordRegex);

        if (!matches) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        }

        return matches;
    }
}