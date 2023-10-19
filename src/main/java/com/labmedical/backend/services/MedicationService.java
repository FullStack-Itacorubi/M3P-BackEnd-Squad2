package com.labmedical.backend.services;

import com.labmedical.backend.controllers.MedicationController;
import com.labmedical.backend.repositories.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicationService {

    @Autowired
    private MedicationRepository medicationRepository;
}
