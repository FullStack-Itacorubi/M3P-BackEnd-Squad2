package com.labmedical.backend.controllers;

import com.labmedical.backend.services.medications.MedicationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medications")
public class MedicationController {

    @Autowired
    private MedicationServiceImpl medicationServiceImpl;
}
