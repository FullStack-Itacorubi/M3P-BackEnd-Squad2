package com.labmedical.backend.dtos.Users;

import com.labmedical.backend.entities.Person;
import com.labmedical.backend.entities.UsersType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateUsersRequestDTO (
        @NotBlank(message = "Full name is required")
        @Size(min = 8, max = 64, message = "Full name must be between 8 and 64 characters")
        String fullName,

        @NotNull(message = "Gender is required")
        Person.Gender gender,

        @Pattern(regexp = "\\(\\d{2}\\)\\s?\\d{4,5}-\\d{4}", message = "Invalid phone number format (e.g., (XX) XXXX-XXXX or (XX) XXXXX-XXXX)")
        @NotBlank(message = "Phone number is required")
        String phone,

        @NotBlank(message = "Password is required")
        @Size(min = 6, message = "Password must be at least 6 characters")
        String password,

        @NotNull(message = "User type is required")
        UsersType type,

        @NotNull(message = "System status is required")
        Boolean systemStatus
){

}

