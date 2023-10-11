package com.labmedical.backend.services.Users;

import com.labmedical.backend.dtos.Users.CreateUsersRequestDTO;
import com.labmedical.backend.dtos.Users.LoginRequestDTO;
import com.labmedical.backend.dtos.Users.LoginResponseDTO;
import com.labmedical.backend.dtos.Users.ResetPasswordRequestDTO;
import com.labmedical.backend.entities.Users;

public interface UsersService {
  LoginResponseDTO login(LoginRequestDTO request);

  void resetPassword(ResetPasswordRequestDTO resetPasswordRequest);

  Long createUser(Users user);
}
