package com.labmedical.backend.dtos.diets;

import com.labmedical.backend.entities.Diet;
import com.labmedical.backend.entities.Patient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

public record ResponseDietDTO(

        Long id,
        @NotBlank(message = "is required")
        @Size(min = 5, max = 100, message = "Diet Name must be between 5 and 100 characters")
        String dietName,

        @NotNull(message = "is required")
        LocalDate date,

        @NotNull(message = "is required")
        LocalTime time,

        @NotNull(message = "is required")
        Diet.DietType dietType,

        @NotBlank(message = "is required")
        @Size(min = 1, message = "Description cannot be empty")
        String description,

        @NotNull
        Patient patient,

        @NotNull(message = "System Status is required")
        Boolean systemStatus

) {
}
