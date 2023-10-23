package com.labmedical.backend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @NotBlank(message = "Reason is required")
    @Size(min = 8, message = "Reason must have at least 8 characters")
    private String reason;

    @NotNull(message = "Consultation date is required")
    private LocalDate consultationDate;

    @NotNull(message = "Consultation time is required")
    private LocalTime consultationTime;

    @NotBlank(message = "Problem description is required")
    @Size(min = 16, max = 1024, message = "Problem description must be between 16 and 1024 characters")
    private String problemDescription;

    private Boolean medicationPrescribed = false;

    @NotBlank(message = "Dosage and precautions is required")
    @Size(min = 16, max = 256, message = "Dosage and precautions must be between 16 and 256 characters")
    private String dosageAndPrecautions;

    @NotNull(message = "Status is required")
    private Boolean systemStatus;

}

