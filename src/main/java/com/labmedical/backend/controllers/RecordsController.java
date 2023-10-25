package com.labmedical.backend.controllers;

import com.labmedical.backend.dtos.patients.RecordsDTO;
import com.labmedical.backend.services.patients.PatientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class RecordsController {

    @Autowired
    private PatientServiceImpl patientServiceImpl;
    @GetMapping("/prontuarios")
    public ResponseEntity<List<RecordsDTO>> listRecords(
            @RequestParam(name = "nomePaciente", required = false) String nomePaciente,
            @RequestParam(name = "idPaciente", required = false) Long idPaciente) {

        List<RecordsDTO> prontuarios;

        if (nomePaciente != null) {
            prontuarios = new ArrayList<>();
            prontuarios.add(patientServiceImpl.searchRecordsByName(nomePaciente));
        } else if (idPaciente != null) {
            prontuarios = new ArrayList<>();
            prontuarios.add(patientServiceImpl.searchRecordsById(idPaciente));
        } else {
            prontuarios = patientServiceImpl.listAllProntuarios();
        }

        return ResponseEntity.status(HttpStatus.OK).body(prontuarios);
    }

}
