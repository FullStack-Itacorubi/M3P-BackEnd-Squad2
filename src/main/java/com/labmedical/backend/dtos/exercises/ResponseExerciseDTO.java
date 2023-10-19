package com.labmedical.backend.dtos.exercises;

import com.labmedical.backend.entities.Exercise;
<<<<<<< HEAD
import com.labmedical.backend.entities.Patient;
=======
>>>>>>> f704553cb2ca39283a984f7b6f8b99ed375500a0
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;
import java.time.LocalTime;

public record ResponseExerciseDTO(

        Long id,

        @NotBlank(message = "Exercise name is required")
        @Size(min = 5, max = 100, message = "Exersise name must be between 05 and 100 characters")
        String exerciseName,

        @NotNull(message = "Date is required")
        LocalDate date,

        @NotNull(message = "Time is required")
        LocalTime time,

        @NotNull(message = "Type is required")
<<<<<<< HEAD
        Exercise.ExerciseType exerciseType,
=======
        Exercise.Type type,
>>>>>>> f704553cb2ca39283a984f7b6f8b99ed375500a0

        @NotNull(message = "Exercise weekly amount value may not be empty")
        @Range(min = 1, max = 7, message = "Exercise weekly amount value must be between 1 and 7")
        Integer weeklyAmount,

        @NotBlank(message = "Exercise name is required")
        @Size(min = 10, max = 1000, message = "Exercise description must be between 10 and 1000 characters")
        String description,

<<<<<<< HEAD
        @NotNull
        Patient patient,

=======
>>>>>>> f704553cb2ca39283a984f7b6f8b99ed375500a0
        @NotNull(message = "System Status is required")
        Boolean systemStatus
        ) {
}
