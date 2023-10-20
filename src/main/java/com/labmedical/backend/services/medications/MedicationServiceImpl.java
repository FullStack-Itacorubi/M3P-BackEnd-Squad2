package com.labmedical.backend.services.medications;

import com.labmedical.backend.repositories.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicationServiceImpl implements MedicationService{

    @Autowired
    private MedicationRepository medicationRepository;
}
