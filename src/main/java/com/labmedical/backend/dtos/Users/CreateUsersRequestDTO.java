package com.labmedical.backend.dtos.Users;

import com.labmedical.backend.entities.Person;
import com.labmedical.backend.entities.enums.UserType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUsersRequestDTO(
        @NotBlank
        String fullName,
        @NotNull
        Person.Gender gender,
        @NotBlank
        String cpf,
        @NotBlank
        String phone,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password,
        @NotNull
        @Enumerated(EnumType.STRING)
        UserType type,
        @NotNull
        boolean systemStatus
) {

}