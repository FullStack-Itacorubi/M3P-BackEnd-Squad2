package com.labmedical.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.apache.commons.lang3.EnumUtils;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "Exercise name is required")
    @Size(min = 5, max = 100, message = "Exersise name must be between 05 and 100 characters")
    @Column(name = "exercise_name",nullable = false)
    private String exerciseName;

    @NotNull(message = "Date is required")
    @Column(name = "date",nullable = false)
    private LocalDate date;

    @NotNull(message = "Time is required")
    @Column(name = "time",nullable = false)
    private LocalTime time;

    @NotNull(message = "Type is required")
    @Column(name = "exercise_type",nullable = false)
    @Enumerated(EnumType.STRING)
    private ExerciseType exerciseType;

    @NotNull(message= "Exercise weekly amount value may not be empty")
    @Column(name = "weekly_amount",nullable = false)
    @Range(min = 1, max = 7, message = "Exercise weekly amount value must be between 1 and 7")
    private Integer weeklyAmount;

    @NotBlank(message = "Exercise name is required")
    @Size(min = 10, max = 1000, message = "Exercise description must be between 10 and 1000 characters")
    @Column(name = "description",nullable = false)
    private String description;

    @NotNull(message = "System Status is required")
    @Column(name = "system_status")
    private Boolean systemStatus = true;

    @NotNull(message = "Patient is required")
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;




    public enum ExerciseType {
        AEROBIC_RESISTANCE,
        MUSCULAR_RESISTANCE,
        FLEXIBILITY,
        STRENGTH,
        AGILITY,
        OTHER
    }

    @PrePersist
    @PreUpdate
    private void validateEnumValues() {
        if (exerciseType != null && !EnumUtils.isValidEnum(Exercise.ExerciseType.class, exerciseType.name())) {
            throw new IllegalArgumentException("Invalid Exercise Type");
        }
    }
}
