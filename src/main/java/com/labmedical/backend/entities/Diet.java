package com.labmedical.backend.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.EnumUtils;

@Entity
@Table(name = "diets")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Diet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Diet Name is required")
    @Size(min = 5, max = 100, message = "Diet Name must be between 5 and 100 characters")
    @Column(name = "diet_name")
    private String dietName;

    @NotNull(message = "Date is required")
    @Column(name = "date")
    private LocalDate date = LocalDate.now();

    @NotNull(message = "Time is required")
    @Column(name = "time")
    private LocalTime time = LocalTime.now();

    @NotBlank(message = "Type is required")
    @Enumerated(EnumType.STRING)
    private DietType type;

    @NotBlank(message = "Description is required")
    @Size(min = 1, message = "Description cannot be empty")
    private String description;

    @NotNull(message = "System Status is required")
    @Column(name = "system_status")
    private Boolean systemStatus = true;

    public enum DietType {
        LOW_CARB,
        DASH,
        PALEOLITHIC,
        KETOGENIC,
        DUKAN,
        MEDITERRANEAN,
        OTHER
    }

    @PrePersist
    @PreUpdate
    private void validateEnumValues() {
        if (type != null && !EnumUtils.isValidEnum(Diet.DietType.class, type.name())) {
            throw new IllegalArgumentException("Invalid Diet Type");
        }
    }
}
