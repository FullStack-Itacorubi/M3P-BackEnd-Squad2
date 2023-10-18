package com.labmedical.backend.dtos.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserTypeValidator.class)
@Documented
public @interface ValidUserType {
    String message() default "Invalid  ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

