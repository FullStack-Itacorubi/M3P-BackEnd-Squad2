package com.labmedical.backend.dtos.exercises;

import com.labmedical.backend.entities.Exercise;
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
        Exercise.ExerciseType exerciseType,

        @NotNull(message = "Exercise weekly amount value may not be empty")
        @Range(min = 1, max = 7, message = "Exercise weekly amount value must be between 1 and 7")
        Integer weeklyAmount,

        @NotBlank(message = "Exercise name is required")
        @Size(min = 10, max = 1000, message = "Exercise description must be between 10 and 1000 characters")
        String description,

        @NotNull(message = "System Status is required")
        Boolean systemStatus
        ) {
}
