package com.labmedical.backend.controllers;

import com.labmedical.backend.dtos.Users.UpdateUsersRequestDTO;
import com.labmedical.backend.dtos.Users.*;
import com.labmedical.backend.entities.Users;
import com.labmedical.backend.mappers.UsersMapper;
import com.labmedical.backend.repositories.UsersRepository;
import com.labmedical.backend.services.Users.UsersServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsersController {

    @Autowired
    private UsersServiceImpl usersService;
    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Validated @RequestBody LoginRequestDTO loginRequest) {
        try {
            LoginResponseDTO response = usersService.login(loginRequest);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
    @PatchMapping ("/res")
    public ResponseEntity<Object> resetPassword(@Validated @RequestBody ResetPasswordRequestDTO resetPasswordRequest) {
        try {
            usersService.resetPassword(resetPasswordRequest);
            return ResponseEntity.ok("Password reset successful.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
    @PostMapping
    public ResponseEntity<Object> createUser(@Validated @RequestBody CreateUsersRequestDTO usersRequestDTO) {
        Users user = UsersMapper.INSTANCE.createUserRequestDTOToUser(usersRequestDTO);
        try {
            usersService.createUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException ex)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF or email already exists.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage()+"Internal Error");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(
            @PathVariable Long id,
            @Validated @RequestBody UpdateUsersRequestDTO updateUserRequest) {
        try {
            Users existingUser = usersRepository.getUserById(id);

            if (existingUser == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
            }
            usersService.updateUser(existingUser, updateUserRequest);
            return ResponseEntity.ok(usersRepository.getUserById(existingUser.getId()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        try {
            List<Users> users = usersRepository.findAll();
            List<CreateUsersResponseDTO> userDTOs = users.stream()
                    .map(UsersMapper.INSTANCE::userToCreateUserResponseDTO)
                    .collect(Collectors.toList());
            if (userDTOs.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users found.");
            }
            return ResponseEntity.ok(userDTOs);
        } catch (DataAccessException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error");
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Error");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        try {
            UserResponseDTO user = usersService.getUserById(id);

            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
            }

            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Error");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        try {
            Users existingUser = usersRepository.getUserById(id);

            if (existingUser == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
            }

            usersRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("User deleted successfully");
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request: " + ex.getMessage());
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Error");
        }
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errorMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Throwable mostSpecificCause = ex.getMostSpecificCause();
        if (mostSpecificCause != null) {
            String errorMessage = mostSpecificCause.getMessage();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request");
    }
}
