package com.labmedical.backend.controllers;

import com.labmedical.backend.dtos.medications.RequestMedicationDTO;
import com.labmedical.backend.dtos.medications.ResponseMedicationDTO;
import com.labmedical.backend.mappers.MedicationMapper;
import com.labmedical.backend.services.medications.MedicationService;
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
@RequestMapping("medicamentos")
public class MedicationController {

    @Autowired
    private MedicationService medicationService;

    @Autowired
    private MedicationMapper medicationMapper;

    @PostMapping
    public ResponseEntity<ResponseMedicationDTO > createMedication(
            @Validated @RequestBody RequestMedicationDTO requestMedicationDTO
            , @RequestParam Long patientId
    ) {
            return new ResponseEntity<>(medicationService.saveMedication(requestMedicationDTO
                    , patientId) ,HttpStatus.CREATED);
      }


    @PutMapping("/{id}")
    public ResponseEntity<ResponseMedicationDTO> updateMedication(
            @PathVariable Long id,
            @Validated @RequestBody RequestMedicationDTO requestMedicationDTO) {
        return new ResponseEntity<>(medicationService.updateMedication(id, requestMedicationDTO), HttpStatus.OK);

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
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medication not found at the database");
    }

}
