package com.labmedical.backend.controllers;

import com.labmedical.backend.dtos.patients.GetResponsePatientDTO;
import com.labmedical.backend.dtos.patients.PostRequestPatientDTO;
import com.labmedical.backend.dtos.patients.PostResponsePatientDTO;
import com.labmedical.backend.services.PatientService;
import jakarta.validation.ConstraintViolationException;
import com.labmedical.backend.dtos.patients.PutRequestPatientDTO;
import com.labmedical.backend.services.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/pacientes")
public class PatientController {

    @Autowired
    private PatientServiceImpl patientServiceImpl;

    @PostMapping
    public ResponseEntity<PostResponsePatientDTO> savePatient(@Validated @RequestBody PostRequestPatientDTO patient) {

            return new ResponseEntity<>(patientServiceImpl.savePatient(patient), HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<GetResponsePatientDTO>> listAllPatients() {
        return ResponseEntity.ok(patientServiceImpl.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<GetResponsePatientDTO> findById(@PathVariable Long id) {

        return ResponseEntity.ok(patientServiceImpl.findPatientById(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponsePatientDTO> updatePatient(
            @PathVariable Long id,
            @Validated @RequestBody PutRequestPatientDTO patient
    ) {

        return new ResponseEntity<>(patientServiceImpl.replacePatientData(id, patient), HttpStatus.OK);

    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {

        patientServiceImpl.deletePatient(id);

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
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        Throwable mostSpecificCause = ex.getMostSpecificCause();

        String errorMessage = mostSpecificCause.getMessage();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex) {
        Throwable errorCause = ex.getCause();
        if (errorCause != null) {
            String errorMessage = errorCause.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found at the database");
    }

}
