package com.labmedical.backend.services.statistics;

import com.labmedical.backend.dtos.statistics.ResponseStatisticsDTO;
import com.labmedical.backend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl implements StatisticService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    private DietRepository dietRepository;

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Override
    public ResponseStatisticsDTO getStatistics() {

        return new ResponseStatisticsDTO(
                (long) patientRepository.findAll().size(),
                (long) examRepository.findAll().size(),
                (long) medicationRepository.findAll().size(),
                (long) dietRepository.findAll().size(),
                (long) exerciseRepository.findAll().size(),
                (long) appointmentRepository.findAll().size()
        );
    }
}
