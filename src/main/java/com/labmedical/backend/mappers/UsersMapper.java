package com.labmedical.backend.mappers;

import com.labmedical.backend.dtos.Users.CreateUsersRequestDTO;
import com.labmedical.backend.dtos.Users.CreateUsersResponseDTO;
import com.labmedical.backend.dtos.Users.LoginRequestDTO;
import com.labmedical.backend.dtos.Users.LoginResponseDTO;

import com.labmedical.backend.entities.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UsersMapper {
    UsersMapper INSTANCE = Mappers.getMapper( UsersMapper.class );
    @Mapping(target="name", source = "fullName")
    @Mapping(target="status", source="systemStatus")
    Users createUserRequestDTOToUser(CreateUsersRequestDTO createUserRequest);

    CreateUsersResponseDTO userToCreateUserResponseDTO(Users user);

    LoginRequestDTO userToUserLoginRequestDTO(Users user);

    LoginResponseDTO userToUserLoginResponseDTO(Users user);

    Users userLoginRequestDTOToUser(LoginRequestDTO dto);
}
