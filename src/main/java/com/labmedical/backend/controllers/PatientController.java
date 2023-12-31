package com.labmedical.backend.controllers;

import com.labmedical.backend.dtos.patients.RequestPatientDTO;
import com.labmedical.backend.dtos.patients.ResponsePatientDTO;
import com.labmedical.backend.services.patients.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/pacientes")
public class PatientController {

    @Autowired
    private PatientServiceImpl patientServiceImpl;

    @PostMapping
    public ResponseEntity<ResponsePatientDTO> savePatient(@Validated @RequestBody RequestPatientDTO patient) {

        return new ResponseEntity<>(patientServiceImpl.savePatient(patient), HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<ResponsePatientDTO>> listAllPatients() {
        return ResponseEntity.ok(patientServiceImpl.findAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePatientDTO> findById(@PathVariable Long id) {

        return ResponseEntity.ok(patientServiceImpl.findPatientById(id));

    }


    @PutMapping("/{id}")
    public ResponseEntity<ResponsePatientDTO> updatePatient(
            @PathVariable Long id,
            @Validated @RequestBody RequestPatientDTO patient
    ) {

        return new ResponseEntity<>(patientServiceImpl.updatePatientData(id, patient), HttpStatus.OK);

    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable Long id) {
        try {
            patientServiceImpl.deletePatient(id);
            return ResponseEntity.status(HttpStatus.OK).body("Patient deleted successfully.");
        } catch (PatientServiceImpl.PatientHasRecordsException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (PatientServiceImpl.PatientNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }




    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errorMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + " : " + fieldError.getDefaultMessage())
                .toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {

//        Throwable mostSpecificCause = ex.getMostSpecificCause();
//
//        String errorMessage = mostSpecificCause.getMessage();
//        String msg = ex.getLocalizedMessage();
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);

        Throwable mostSpecificCause = ex.getMostSpecificCause();

        if (mostSpecificCause instanceof DateTimeParseException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("All dates need to be in format " +
                    "\"yyyy-MM-dddd\"");
        }
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
