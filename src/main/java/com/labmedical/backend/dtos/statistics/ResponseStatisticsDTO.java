package com.labmedical.backend.dtos.statistics;

public record ResponseStatisticsDTO(

        Long numbOfPatients,

        Long numbOfExams,

        Long numbOfMedications,

        Long numbOfDiets,

        Long numbOfExercises,

        Long numbOfAppointments
) {
}
