package com.labmedical.backend.controllers;

import com.labmedical.backend.dtos.patients.GetResponsePatientDTO;
import com.labmedical.backend.dtos.patients.PostRequestPatientDTO;
import com.labmedical.backend.dtos.patients.PostResponsePatientDTO;
import com.labmedical.backend.services.PatientService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping
    public ResponseEntity<PostResponsePatientDTO> savePatient(@Validated @RequestBody PostRequestPatientDTO patient) {
        try {
            return new ResponseEntity<>(patientService.savePatient(patient), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException ex) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Duplicated data: CPF or e-mail already exists", ex);
        } catch (ConstraintViolationException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid data", ex);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public ResponseEntity<List<GetResponsePatientDTO>> listAllPatients() {
        try {
            return ResponseEntity.ok(patientService.findAll());
        } catch (ConstraintViolationException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid data", ex);
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Server error", ex);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetResponsePatientDTO> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(patientService.findPatientById(id));
        } catch (ConstraintViolationException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid data", ex);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        }
    }
}
