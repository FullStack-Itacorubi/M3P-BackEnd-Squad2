package com.labmedical.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity(name = "addresses")
@Table(name = "addresses")
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "CEP is required")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "Invalid CEP format (e.g., 00000-000")
    private String cep;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "Street is required")
    private String street;

    @NotBlank(message = "Number is required")
    private String number;

    private String complement;

    @NotBlank(message = "District is required")
    private String district;

    private String reference;

}