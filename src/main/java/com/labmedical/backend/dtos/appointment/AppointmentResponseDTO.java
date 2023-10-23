package com.labmedical.backend.dtos.appointment;

import com.labmedical.backend.entities.Patient;
import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentResponseDTO(
        Long id,
        Patient patient,
        String reason,
        LocalDate consultationDate,
        LocalTime consultationTime,
        String problemDescription,
        Boolean medicationPrescribed,
        String dosageAndPrecautions,
        Boolean systemStatus
) {

}
