package com.labmedical.backend.dtos.Users;


import com.labmedical.backend.entities.UsersType;

public record CreateUsersResponseDTO (
        Long id,
        String fullName,
        String email,
        String phone,
        String gender,
        String password,
        Boolean systemStatus,
        UsersType type
) {
}
