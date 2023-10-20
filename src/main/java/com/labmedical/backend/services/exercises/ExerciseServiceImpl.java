package com.labmedical.backend.services.exercises;

import com.labmedical.backend.dtos.exercises.RequestExerciseDTO;
import com.labmedical.backend.dtos.exercises.ResponseExerciseDTO;
import com.labmedical.backend.entities.Diet;
import com.labmedical.backend.entities.Exercise;
import com.labmedical.backend.entities.Patient;
import com.labmedical.backend.mappers.ExerciseMapper;
import com.labmedical.backend.repositories.ExerciseRepository;
import com.labmedical.backend.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    @Autowired
    private ExerciseMapper exerciseMapper;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public ResponseExerciseDTO createExercise(RequestExerciseDTO requestExerciseDTO, Long patientId) {
        Optional<Patient> patientOptional = patientRepository.findById(patientId);

        if(patientOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        }
        Exercise exerciseToSave = exerciseMapper.map(requestExerciseDTO);
        exerciseToSave.setSystemStatus(true);
        exerciseToSave.setPatient(patientOptional.get());

        return exerciseMapper
                .mapToResponseExerciseDTO(exerciseRepository.save(exerciseToSave));
    }

    @Override
    public ResponseExerciseDTO updateExercise(Long id, RequestExerciseDTO requestExerciseDTO) {
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(id);
        if (exerciseOptional.isEmpty()) {
            throw new NoSuchElementException();
        }
        Long patientId = exerciseOptional.get().getPatient().getId();
        Optional<Patient> patientOptional = patientRepository.findById(patientId);

        if(patientOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        }

        Exercise exerciseToUpdate = exerciseMapper.map(requestExerciseDTO);
        exerciseToUpdate.setPatient(patientOptional.get());
        exerciseToUpdate.setId(id);

        return exerciseMapper.mapToResponseExerciseDTO(exerciseRepository.save(exerciseToUpdate));
    }

    @Override
    public ResponseExerciseDTO findExerciseById(Long id) {
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(id);
        if (exerciseOptional.isEmpty()) {
            throw new NoSuchElementException();
        }
        return exerciseMapper.mapToResponseExerciseDTO(exerciseOptional.get());
    }

    @Override
    public void deleteExerciseById(Long id) {
        Optional<Exercise> exerciseOptional = exerciseRepository.findById(id);
        if (exerciseOptional.isEmpty()) {
            throw new NoSuchElementException();
        }else{
            exerciseRepository.delete(exerciseOptional.get());
        }
    }
}
