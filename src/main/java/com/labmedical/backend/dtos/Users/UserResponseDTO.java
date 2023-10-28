package com.labmedical.backend.dtos.Users;

import com.labmedical.backend.entities.Person;
import com.labmedical.backend.entities.UsersType;

public record UserResponseDTO(
        Long id,
        String fullName,
        Person.Gender gender,
        String cpf,
        String phone,
        String email,
        UsersType type,
        boolean systemStatus
) {
}