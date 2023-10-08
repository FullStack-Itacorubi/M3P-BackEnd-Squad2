package com.labmedical.backend.mappers;

import com.labmedical.backend.dtos.Users.LoginRequestDTO;
import com.labmedical.backend.dtos.Users.LoginResponseDTO;

import com.labmedical.backend.entities.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsersMapper {
    LoginRequestDTO userToUserLoginRequestDTO(Users user);

    @Mapping(target = "password", ignore = true)
    LoginResponseDTO userToUserLoginResponseDTO(Users user);

    Users userLoginRequestDTOToUser(LoginRequestDTO dto);
}
