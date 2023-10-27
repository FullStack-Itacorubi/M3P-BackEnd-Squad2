package com.labmedical.backend.mappers;
import com.labmedical.backend.dtos.Users.*;
import com.labmedical.backend.entities.Users;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UsersMapper {
    UsersMapper INSTANCE = Mappers.getMapper(UsersMapper.class);

    @Mapping(target = "name", source = "fullName")
    @Mapping(target = "status", source = "systemStatus")
    Users createUserRequestDTOToUser(CreateUsersRequestDTO createUserRequest);

    CreateUsersResponseDTO userToCreateUserResponseDTO(Users user);

    LoginRequestDTO userToUserLoginRequestDTO(Users user);

    LoginResponseDTO userToUserLoginResponseDTO(Users user);

    Users userLoginRequestDTOToUser(LoginRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "fullName", qualifiedByName = "updateName")
    @Mapping(target = "status", source = "systemStatus", qualifiedByName = "updateStatus")
    Users UpdateUsersRequestDTOtoUsers(UpdateUsersRequestDTO dto, @Context Users existingUser);

    @Named("updateName")
    default String updateName(String fullName, @Context Users existingUser) {
        return fullName != null ? fullName : existingUser.getName();
    }

    @Named("updateStatus")
    default Boolean updateStatus(Boolean systemStatus, @Context Users existingUser) {
        return systemStatus != null ? systemStatus : existingUser.getStatus();
    }
}
