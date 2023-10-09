package com.labmedical.backend.controllers;

import com.labmedical.backend.dtos.patients.PatientPostRequestDTO;
import com.labmedical.backend.dtos.patients.PatientPostResponseDTO;
import com.labmedical.backend.services.PatientService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientPostResponseDTO> savePatient(@RequestBody @Valid PatientPostRequestDTO patient) {
//        try {
            return new ResponseEntity<>(patientService.savePatient(patient), HttpStatus.CREATED);
//        } catch (DataIntegrityViolationException ex) {
//            throw new ResponseStatusException(
//                    HttpStatus.CONFLICT, "Duplicated data: CPF or e-mail already exists", ex);
//        } catch (ConstraintViolationException ex) {
//            throw new ResponseStatusException(
//                    HttpStatus.BAD_REQUEST, "Invalid data", ex);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }
}
