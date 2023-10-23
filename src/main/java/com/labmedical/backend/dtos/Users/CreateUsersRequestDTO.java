package com.labmedical.backend.dtos.Users;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.labmedical.backend.dtos.annotations.UserTypeDeserializer;
import com.labmedical.backend.dtos.annotations.ValidUserType;
import com.labmedical.backend.entities.Person;
import com.labmedical.backend.entities.UsersType;
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

        @JsonDeserialize(using = UserTypeDeserializer.class)
        @ValidUserType
        @NotNull
        @Enumerated(EnumType.STRING)
        UsersType type,
        @NotNull
        boolean systemStatus
) {

}
