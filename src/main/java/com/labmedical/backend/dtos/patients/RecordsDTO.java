package com.labmedical.backend.dtos.patients;
import com.labmedical.backend.entities.*;

import java.util.List;

public record RecordsDTO(
        String name,
        String emergencyContact,
        String allergies,
        String specificCare,
        String insurance,
        List<Exercise> exerciseList,
        List<Diet> dietList,
        List<Exam> examList,
        List<Appointment> appointmentList,
        List<Medication> medicationList
) {
}
