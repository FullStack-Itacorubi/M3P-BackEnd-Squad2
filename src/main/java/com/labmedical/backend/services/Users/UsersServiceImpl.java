package com.labmedical.backend.services.Users;

import com.labmedical.backend.dtos.Users.LoginRequestDTO;
import com.labmedical.backend.dtos.Users.LoginResponseDTO;
import com.labmedical.backend.dtos.Users.ResetPasswordRequestDTO;
import com.labmedical.backend.dtos.Users.UpdateUsersRequestDTO;
import com.labmedical.backend.entities.Users;
import com.labmedical.backend.mappers.UsersMapper;
import com.labmedical.backend.repositories.UsersRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {
        Users user = usersRepository.findByEmail(request.email());

        if (user != null && user.getPassword().equals(request.password())) {
            return new LoginResponseDTO("Authentication successful");
        } else {
            throw new RuntimeException("Authentication failed. Check your email and password.");
        }
    }

    @Override
    public void resetPassword(ResetPasswordRequestDTO resetPasswordRequest) {
        Users user = usersRepository.findByEmail(resetPasswordRequest.email());
        if (user == null) {
            throw new IllegalArgumentException("User not found.");
        }
        if (!resetPasswordRequest.oldPassword().equals(user.getPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }
        user.setPassword(resetPasswordRequest.newPassword());
        usersRepository.save(user);
    }

    @Override
    public Long createUser(Users user) {
        if (usersRepository.existsByCpfOrEmail(user.getCpf(), user.getEmail())) {
            throw new DataIntegrityViolationException("CPF or email already exists.");
        }
        Users savedUser = usersRepository.save(user);
        return savedUser.getId();
    }

    @Override
    public Long updateUser(Users existingUser, UpdateUsersRequestDTO updateUserRequest) {
        Users updatedUser = usersRepository.findById(existingUser.getId())
                .orElseThrow(() -> new EntityNotFoundException("User not found."));

        UsersMapper.INSTANCE.UpdateUsersRequestDTOtoUsers(updateUserRequest, updatedUser);

        usersRepository.save(updatedUser);

        return updatedUser.getId();
    }
}
