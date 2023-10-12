package com.labmedical.backend.dtos.Users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResetPasswordRequestDTO(
        @NotNull Long id,
        @NotBlank(message = "Email is required") @Email(message = "Invalid email address") String email,
        @NotBlank(message = "Password is required") String password)
 {
}
