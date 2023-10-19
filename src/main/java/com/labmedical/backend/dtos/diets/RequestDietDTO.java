package com.labmedical.backend.dtos.diets;

import com.labmedical.backend.entities.Diet;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalTime;

public record RequestDietDTO(
        @NotBlank(message = "Diet Name is required")
        @Size(min = 5, max = 100, message = "Diet Name must be between 5 and 100 characters")
        String dietName,

        @NotNull(message = "Date is required")
        LocalDate date,

        @NotNull(message = "Time is required")
        LocalTime time,

        @NotNull(message = "Diet type is required")
        Diet.DietType dietType,

        @NotBlank(message = "Description is required")
        @Size(min = 1, message = "Description cannot be empty")
        String description,

        Boolean systemStatus
) {
}
