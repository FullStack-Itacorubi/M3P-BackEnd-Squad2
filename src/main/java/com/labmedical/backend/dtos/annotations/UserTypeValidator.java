package com.labmedical.backend.dtos.annotations;

import com.labmedical.backend.entities.UsersType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserTypeValidator implements ConstraintValidator<ValidUserType, UsersType> {
    @Override
    public void initialize(ValidUserType constraintAnnotation) {
    }

    @Override
    public boolean isValid(UsersType userType, ConstraintValidatorContext context) {
        if (userType == null) {
            return false;
        }
        return userType == UsersType.ADMINISTRATOR || userType == UsersType.DOCTOR || userType == UsersType.NURSE;
    }
}
