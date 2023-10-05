package com.labmedical.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity(name = "addresses")
@Table(name = "tb_addresses")
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank(message = "CEP is required")
    String cep;

    @NotBlank(message = "City is required")
    String city;

    @NotBlank(message = "State is required")
    String state;

    @NotBlank(message = "Street is required")
    String street;

    @NotBlank(message = "Number is required")
    String number;

    String complement;

    @NotBlank(message = "District is required")
    String district;

    String reference;


}
