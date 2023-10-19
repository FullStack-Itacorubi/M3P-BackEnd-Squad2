package com.labmedical.backend.controllers;


import com.labmedical.backend.dtos.diets.GetResponseDietDTO;
import com.labmedical.backend.dtos.diets.PostRequestDietDTO;
import com.labmedical.backend.dtos.diets.PostResponseDietDTO;

import com.labmedical.backend.mappers.DietMapper;
import com.labmedical.backend.services.DietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
            @Validated @RequestBody PostRequestDietDTO postRequestDietDTO
            , @RequestParam Long patientId
    ) {
            return new ResponseEntity<>(dietService.createDiet(postRequestDietDTO
                    , patientId
            ) ,HttpStatus.CREATED);
      }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDietDTO> updateDiet(
            @PathVariable Long id,
            @Validated @RequestBody PostRequestDietDTO postRequestDietDTO) {
        return new ResponseEntity<>(dietService.updateDiet(id, postRequestDietDTO), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<GetResponseDietDTO> getDietById(@PathVariable Long id){
        return new ResponseEntity<>(dietService.findDietById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteDietById(@PathVariable Long id){

        dietService.deleteDietById(id);

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

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Object> handleNoSuchElementException(ResponseStatusException ex) {
        Throwable errorCause = ex.getCause();
        if (errorCause != null) {
            String errorMessage = errorCause.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found at the database");
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex) {
        Throwable errorCause = ex.getCause();
        if (errorCause != null) {
            String errorMessage = errorCause.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Diet not found at the database");
    }



}
