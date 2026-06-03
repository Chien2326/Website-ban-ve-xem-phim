package hethongwebbanvexemphim.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false;
        }

        // At least 7 characters
        if (password.length() < 7) {
            return false;
        }

        // Has at least one uppercase letter (A-Z)
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }

        // Has at least one lowercase letter (a-z)
        if (!password.matches(".*[a-z].*")) {
            return false;
        }

        // Has at least one digit (0-9)
        if (!password.matches(".*[0-9].*")) {
            return false;
        }

        // Has at least one special character (non-alphanumeric)
        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")) {
            return false;
        }

        return true;
    }
}
