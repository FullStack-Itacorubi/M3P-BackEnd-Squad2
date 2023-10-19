package com.labmedical.backend.dtos.exams;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;
import java.time.LocalTime;

public record GetResponseExamDTO(
        Long id,

        @NotBlank(message = "Exam Name is required")
        @Size(min = 8, max = 64, message = "Exam Name must be between 8 and 64 characters")
        String name,

        @NotNull(message = "Date is required")
        LocalDate date,

        @NotNull(message = "Time is required")
        LocalTime time,

        @NotBlank(message = "Type is required")
        @Size(min = 4, max = 32, message = "Exam Name must be between 4 and 32 characters")
        String examType,

        @NotBlank(message = "Laboratory Name is required")
        @Size(min = 4, max = 32, message = "Laboratory Name must be between 4 and 32 characters")
        String laboratory,

        @URL(message = "The url is not valid")
        String documentUrl,

        @NotBlank(message = "Results are required")
        @Size(min = 16, max = 1024, message = "Results must be between 16 and 1024 characters")
        String results,

        @NotNull(message = "System Status is required")
        Boolean systemStatus
) {
}
