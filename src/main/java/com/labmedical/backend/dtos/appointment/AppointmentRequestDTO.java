package com.labmedical.backend.dtos.appointment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record AppointmentRequestDTO(
        @NotBlank(message = "Reason is required")
        @Size(min = 8) String reason,
        @NotNull(message = "Consultation date is required") LocalDate consultationDate,
        @NotNull(message = "Consultation time is required") LocalTime consultationTime,
        @NotBlank(message = "Problem description is required") @Size(min = 16, max = 1024) String problemDescription,
        Boolean medicationPrescribed,
        @Size(min = 16, max = 256) String dosageAndPrecautions,
        @NotNull (message="Status is required") Boolean systemStatus
) {
}

