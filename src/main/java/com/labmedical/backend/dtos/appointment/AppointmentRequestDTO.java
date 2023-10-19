package com.labmedical.backend.dtos.appointment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record AppointmentRequestDTO(
        @NotBlank @Size(min = 8) String reason,
        @NotNull LocalDate consultationDate,
        @NotNull LocalTime consultationTime,
        @NotBlank @Size(min = 16, max = 1024) String problemDescription,
        Boolean medicationPrescribed,
        @NotBlank @Size(min = 16, max = 256) String dosageAndPrecautions,
        @NotNull Boolean systemStatus
) {
}

