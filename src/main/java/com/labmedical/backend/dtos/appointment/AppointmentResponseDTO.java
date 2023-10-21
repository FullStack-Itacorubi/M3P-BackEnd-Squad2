package com.labmedical.backend.dtos.appointment;

import java.time.LocalDate;
import java.time.LocalTime;

public record AppointmentResponseDTO(
        Long id,
        String reason,
        LocalDate consultationDate,
        LocalTime consultationTime,
        String problemDescription,
        Boolean medicationPrescribed,
        String dosageAndPrecautions,
        Boolean systemStatus
) {

}
