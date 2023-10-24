package com.labmedical.backend.dtos.annotations.gender;

import com.labmedical.backend.entities.Person;
import com.labmedical.backend.entities.Person.Gender;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidGenderValidator implements ConstraintValidator<ValidGender, Person.Gender> {
    @Override
    public boolean isValid(Gender value, ConstraintValidatorContext context) {
        return value != null;
    }
}

