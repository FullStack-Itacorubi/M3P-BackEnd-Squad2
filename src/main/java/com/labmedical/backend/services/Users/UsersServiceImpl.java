package com.labmedical.backend.services.Users;


import com.labmedical.backend.dtos.Users.LoginRequestDTO;
import com.labmedical.backend.dtos.Users.LoginResponseDTO;
import com.labmedical.backend.entities.Users;
import com.labmedical.backend.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
