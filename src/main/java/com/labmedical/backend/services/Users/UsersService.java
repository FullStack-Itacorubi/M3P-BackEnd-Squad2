package com.labmedical.backend.services.Users;

import com.labmedical.backend.dtos.Users.LoginRequestDTO;
import com.labmedical.backend.dtos.Users.LoginResponseDTO;
import org.springframework.stereotype.Service;

public interface UsersService {
  LoginResponseDTO login(LoginRequestDTO request);
}
