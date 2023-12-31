package com.labmedical.backend.controllers;

import com.labmedical.backend.dtos.diets.RequestDietDTO;
import com.labmedical.backend.dtos.diets.ResponseDietDTO;
import com.labmedical.backend.mappers.DietMapper;
import com.labmedical.backend.services.diets.DietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ResponseDietDTO> createDiet(
            @Validated @RequestBody RequestDietDTO requestDietDTO
            , @RequestParam Long patientId
    ) {
            return new ResponseEntity<>(dietService.createDiet(requestDietDTO
                    , patientId
            ) ,HttpStatus.CREATED);
      }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseDietDTO> updateDiet(
            @PathVariable Long id,
            @Validated @RequestBody RequestDietDTO requestDietDTO) {
        return new ResponseEntity<>(dietService.updateDiet(id, requestDietDTO), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDietDTO> getDietById(@PathVariable Long id){
        return new ResponseEntity<>(dietService.findDietById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ResponseDietDTO>> getDietsByPatientName(
            @RequestParam(required = false, name = "patientName") String patientName) {
        return new ResponseEntity<>(dietService.findAllByName(patientName), HttpStatus.OK);
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
