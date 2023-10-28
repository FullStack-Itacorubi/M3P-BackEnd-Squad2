package com.labmedical.backend.dtos.Users;


import com.labmedical.backend.entities.UsersType;

public record CreateUsersResponseDTO (
        Long id,
        String fullName,
        String email,
        String phone,
        String gender,
        Boolean status,
        UsersType type
) {
}
