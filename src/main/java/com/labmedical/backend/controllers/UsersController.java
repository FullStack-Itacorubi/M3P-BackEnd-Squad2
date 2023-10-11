package com.labmedical.backend.controllers;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.labmedical.backend.dtos.Users.CreateUsersRequestDTO;
import com.labmedical.backend.dtos.Users.LoginRequestDTO;
import com.labmedical.backend.dtos.Users.LoginResponseDTO;
import com.labmedical.backend.dtos.Users.ResetPasswordRequestDTO;
import com.labmedical.backend.entities.Users;
import com.labmedical.backend.mappers.UsersMapper;
import com.labmedical.backend.services.Users.UsersServiceImpl;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Validated @RequestBody LoginRequestDTO loginRequest) {
        try {
            LoginResponseDTO response = usersService.login(loginRequest);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
    @PostMapping("/resetarsenha")
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