package com.labmedical.backend.controllers;

import com.labmedical.backend.dtos.Users.LoginRequestDTO;
import com.labmedical.backend.dtos.Users.LoginResponseDTO;
import com.labmedical.backend.dtos.Users.ResetPasswordRequestDTO;
import com.labmedical.backend.services.Users.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
