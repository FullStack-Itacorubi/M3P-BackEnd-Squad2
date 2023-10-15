package com.labmedical.backend.controllers;

<<<<<<< HEAD
import com.labmedical.backend.dtos.exercises.RequestExerciseDTO;
import com.labmedical.backend.dtos.exercises.ResponseExerciseDTO;
=======
import com.labmedical.backend.dtos.exercises.PostRequestExerciseDTO;
import com.labmedical.backend.dtos.exercises.PostResponseExerciseDTO;
>>>>>>> ab87a59 (feat(create-exercise): add endpoint to POST request and functions on controller and service)
import com.labmedical.backend.mappers.ExerciseMapper;
import com.labmedical.backend.services.ExerciseService;
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
@RequestMapping("exercicios")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private ExerciseMapper exerciseMapper;

    @PostMapping
<<<<<<< HEAD
    public ResponseEntity<ResponseExerciseDTO> createExercise(
            @Validated @RequestBody RequestExerciseDTO requestExerciseDTO) {
        try {
            return new ResponseEntity<>(exerciseService.createExercise(requestExerciseDTO), HttpStatus.CREATED);
=======
    public ResponseEntity<PostResponseExerciseDTO> createExercise(
            @Validated @RequestBody PostRequestExerciseDTO postRequestExerciseDTO) {
        try {
            return new ResponseEntity<>(exerciseService.createExercise(postRequestExerciseDTO), HttpStatus.CREATED);
>>>>>>> ab87a59 (feat(create-exercise): add endpoint to POST request and functions on controller and service)
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Error");
        }
    }

<<<<<<< HEAD
    @PutMapping("/{id}")
    public ResponseEntity<ResponseExerciseDTO> updateExercise(
            @PathVariable Long id,
            @Validated @RequestBody RequestExerciseDTO requestExerciseDTO) {
        return new ResponseEntity<>(exerciseService.updateExercise(id, requestExerciseDTO), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseExerciseDTO> getExexerciseById(@PathVariable Long id){
        return new ResponseEntity<>(exerciseService.findExerciseById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteExerciseById(@PathVariable Long id){

        exerciseService.deleteExerciseById(id);

    }

=======
>>>>>>> ab87a59 (feat(create-exercise): add endpoint to POST request and functions on controller and service)

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
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exercise not found at the database");
    }
}
