package com.labmedical.backend.dtos.annotations.marital_status;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import com.labmedical.backend.entities.Patient.MaritalStatus;

public class ValidMaritalStatusValidator implements ConstraintValidator<ValidMaritalStatus, MaritalStatus> {
    @Override
    public boolean isValid(MaritalStatus value, ConstraintValidatorContext context) {
        return value != null;
    }
}

