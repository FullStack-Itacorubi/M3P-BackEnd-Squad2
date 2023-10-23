package com.labmedical.backend.dtos.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record RequestAddressDTO(

        Long addressId,

        @NotBlank(message = "CEP is required")
        @Pattern(regexp = "\\d{5}-\\d{3}", message = "Invalid CEP format (e.g., 00000-000)")
        String cep,

        @NotBlank(message = "City is required")
        String city,

        @NotBlank(message = "State is required")
        String state,

        @NotBlank(message = "Street is required")
        String street,

        @NotBlank(message = "Number is required")
        String number,

        String complement,

        @NotBlank(message = "District is required")
        String district,

        String reference
) {
}