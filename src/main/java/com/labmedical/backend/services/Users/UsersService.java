package com.labmedical.backend.services.Users;

import com.labmedical.backend.dtos.Users.*;
import com.labmedical.backend.entities.Users;

public interface UsersService {
  LoginResponseDTO login(LoginRequestDTO request);

  void resetPassword(ResetPasswordRequestDTO resetPasswordRequest);

  Long createUser(Users user);

  Long updateUser(Users existingUser, UpdateUsersRequestDTO updateUserRequest);
}
