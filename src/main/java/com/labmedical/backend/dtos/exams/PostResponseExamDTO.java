package com.labmedical.backend.dtos.exams;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;
import java.time.LocalTime;

public record PostResponseExamDTO(

        Long id,

        @NotBlank(message = "is required")
        @Size(min = 8, max = 64, message = "must be between 8 and 64 characters")
        String name,

        @NotNull(message = "is required")
        LocalDate date,

        @NotNull(message = "is required")
        LocalTime time,

        @NotBlank(message = "is required")
        @Size(min = 4, max = 32, message = "must be between 4 and 32 characters")
        String examType,

        @NotBlank(message = "Laboratory Name is required")
        @Size(min = 4, max = 32, message = "name must be between 4 and 32 characters")
        String laboratory,

        @URL(message = "is not valid")
        String documentUrl,

        @NotBlank(message = "are required")
        @Size(min = 16, max = 1024, message = "must be between 16 and 1024 characters")
        String results,

        @NotNull
        Long patientId,

        @NotNull(message = "is required")
        Boolean systemStatus
) {
}
