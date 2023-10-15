package com.labmedical.backend.controllers;

import com.labmedical.backend.dtos.logs.PostRequestDietDTO;
import com.labmedical.backend.dtos.logs.PostResponseDietDTO;
import com.labmedical.backend.mappers.DietMapper;
import com.labmedical.backend.services.DietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("dietas")
public class DietController {

    @Autowired
    private DietService dietService;

    @Autowired
    private DietMapper dietMapper;

    @PostMapping
    public ResponseEntity<PostResponseDietDTO> createDiet(
            @Validated @RequestBody PostRequestDietDTO postRequestDietDTO) {
        try {
            return new ResponseEntity<>(dietService.createDiet(postRequestDietDTO), HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Error");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDietDTO> updateExam(
            @PathVariable Long id,
            @Validated @RequestBody PostRequestDietDTO postRequestDietDTO) {
        return new ResponseEntity<>(dietService.updateDiet(id, postRequestDietDTO), HttpStatus.OK);

    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errorMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                .toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Throwable mostSpecificCause = ex.getMostSpecificCause();

        String errorMessage = mostSpecificCause.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex) {
        Throwable errorCause = ex.getCause();
        if (errorCause != null) {
            String errorMessage = errorCause.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exam not found at the database");
    }

}
