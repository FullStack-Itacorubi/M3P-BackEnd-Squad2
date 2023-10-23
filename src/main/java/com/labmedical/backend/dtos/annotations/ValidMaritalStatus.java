package com.labmedical.backend.dtos.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = { ValidMaritalStatusValidator.class })
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidMaritalStatus {
    String message() default "Invalid marital status";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

