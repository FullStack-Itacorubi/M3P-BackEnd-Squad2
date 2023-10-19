package com.labmedical.backend.dtos.Users;

public record CreateUsersResponseDTO (
        Long id,
        String name,
        String email,
        String phone,
        String gender,
        Boolean status
) {
}
