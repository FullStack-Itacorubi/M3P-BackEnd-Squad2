package com.labmedical.backend.dtos.appointment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

public record UpdateAppointmentRequestDTO(
        @NotBlank(message = "Reason is required")
        @Size(min = 8, message = "Reason must have at least 8 characters")
        String reason,

        @NotNull(message = "Consultation date is required")
        LocalDate consultationDate,

        @NotNull(message = "Consultation time is required")
        LocalTime consultationTime,

        @NotBlank(message = "Problem description is required")
        @Size(min = 16, max = 1024, message = "Problem description must be between 16 and 1024 characters")
        String problemDescription,

        Boolean medicationPrescribed,

        @NotBlank(message = "Dosage and precautions is required")
        @Size(min = 16, max = 256, message = "Dosage and precautions must be between 16 and 256 characters")
        String dosageAndPrecautions,

        @NotNull(message = "Status is required")
        Boolean systemStatus
) {
}
