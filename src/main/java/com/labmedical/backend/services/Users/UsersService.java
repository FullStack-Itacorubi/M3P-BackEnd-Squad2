package com.labmedical.backend.services.Users;

import com.labmedical.backend.dtos.Users.LoginRequestDTO;
import com.labmedical.backend.dtos.Users.LoginResponseDTO;
import com.labmedical.backend.dtos.Users.ResetPasswordRequestDTO;
import org.springframework.stereotype.Service;

public interface UsersService {
  LoginResponseDTO login(LoginRequestDTO request);

  void resetPassword(ResetPasswordRequestDTO resetPasswordRequest);
}
