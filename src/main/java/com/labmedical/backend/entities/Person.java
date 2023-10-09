package com.labmedical.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    @Size(max = 64, message = "Name cannot exceed 64 characters")
    @Size(min = 8, message = "Name must be at least 8 characters")
    private String name;

    @NotNull(message = "Gender is required")
    private Gender gender;

    @NotBlank(message = "CPF is required")
    @Column(unique = true)
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "Invalid CPF format")
    private String cpf;

    @Pattern(regexp = "\\(\\d{2}\\)\\s?\\d{4,5}-\\d{4}", message = "Invalid phone number format (e.g., (XX) XXXX-XXXX or (XX) XXXXX-XXXX)")
    private String phone;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email address")
    private String email;

//    @NotNull(message = "Status is required")
//    private Boolean status;

    public enum Gender {
        MALE,
        FEMALE,
        OTHER
    }
}
