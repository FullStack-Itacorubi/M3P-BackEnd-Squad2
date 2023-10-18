package com.labmedical.backend.dtos.annotations;

import com.labmedical.backend.entities.enums.UserType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserTypeValidator implements ConstraintValidator<ValidUserType, UserType> {
    @Override
    public void initialize(ValidUserType constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserType userType, ConstraintValidatorContext context) {
        if (userType == null) {
            return false; // Null values are considered invalid
        }

        // Add your logic to validate UserType
        return userType == UserType.ADMINISTRATOR || userType == UserType.DOCTOR || userType == UserType.NURSE;
    }
}
