package com.labmedical.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "tb_exams")
@Data
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Exam Name is required")
    @Size(min = 8, max = 64, message = "Exam Name must be between 8 and 64 characters")
    @Column(name = "exam_name")
    private String name;

    @NotNull(message = "Date is required")
    @Column(name = "date")
    private LocalDate date = LocalDate.now();

    @NotNull(message = "Time is required")
    @Column(name = "time")
    private LocalTime time = LocalTime.now();

    @NotBlank(message = "Type is required")
    @Size(min = 4, max = 32, message = "Exam Name must be between 4 and 32 characters")
    private String examType;

    @NotBlank(message = "Laboratory Name is required")
    @Size(min = 4, max = 32, message = "Laboratory Name must be between 4 and 32 characters")
    @Column(name = "laboratory")
    private String laboratory;

    @URL(message = "The url is not valid")
    @Column(name = "document_url")
    private String documentUrl;

    @NotBlank(message = "Results are required")
    @Size(min = 16, max = 1024, message = "Results must be between 16 and 1024 characters")
    private String results;

    @NotNull(message = "System Status is required")
    @Column(name = "system_status")
    private Boolean systemStatus;

    @NotNull(message = "Patient is required")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id")
    private Patient patient;

}
