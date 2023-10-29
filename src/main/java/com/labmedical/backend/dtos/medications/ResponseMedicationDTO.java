package com.labmedical.backend.dtos.medications;

import com.labmedical.backend.entities.Medication;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record ResponseMedicationDTO(

        @NotNull
        Long id,

        @NotBlank(message = "Medication Name is required")
        @Size(min = 5, max = 100, message = "Medication Name must be between 5 and 100 characters")
        String medicationName,

        LocalDate medicationDate,
        LocalTime medicationTime,

        @NotNull(message = "Type is required")
        Medication.MedicationType medicationType,

        @NotNull(message = "Quantity is required")
        @Digits(integer = 5, fraction = 2, message = "Quantity must have at least 2 decimal places")
        BigDecimal quantity,

        @NotNull(message = "Unit is required")
        Medication.Unit unit,

        @NotBlank(message = "Observations are required")
        @Size(min = 10, max = 1000, message = "Observations must be between 10 and 1000 characters")
        String observations,

        @NotNull(message = "System Status is required")
        Boolean systemStatus
) {
}
