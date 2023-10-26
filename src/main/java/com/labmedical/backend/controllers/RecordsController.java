package com.labmedical.backend.controllers;

import com.labmedical.backend.dtos.patients.RecordsDTO;
import com.labmedical.backend.dtos.patients.ResponsePatientDTO;
import com.labmedical.backend.entities.Patient;
import com.labmedical.backend.repositories.PatientRepository;
import com.labmedical.backend.services.patients.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.invoke.ParameterMappingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class RecordsController {

    @Autowired
    private PatientServiceImpl patientServiceImpl;

    @GetMapping("/prontuarios")
    public ResponseEntity<?> listRecords(
            @RequestParam(name = "nomePaciente", required = false) String nomePaciente,
            @RequestParam(name = "idPaciente", required = false) Long idPaciente) {
        try {
            RecordsDTO prontuario;

            if (nomePaciente != null && idPaciente != null) {
                ResponsePatientDTO patientById = patientServiceImpl.findPatientById(idPaciente);
                if (!patientById.name().equals(nomePaciente)) {
                    throw new PatientMismatchException();
                }
            } else if (nomePaciente != null) {
                prontuario = patientServiceImpl.searchRecordsByName(nomePaciente);
                return ResponseEntity.status(HttpStatus.OK).body(prontuario);
            } else if (idPaciente != null) {
                prontuario = patientServiceImpl.searchRecordsById(idPaciente);
                return ResponseEntity.status(HttpStatus.OK).body(prontuario);
            }
            List<RecordsDTO> prontuarios = patientServiceImpl.listAllProntuarios();
            return ResponseEntity.status(HttpStatus.OK).body(prontuarios);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request data");
        } catch (PatientMismatchException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Patient not found");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    public class PatientMismatchException extends RuntimeException {

        public PatientMismatchException() {
            super("Patient ID and name do not match the same record.");
        }
    }
}

