package com.labmedical.backend.services;

import com.labmedical.backend.dtos.diets.GetResponseDietDTO;
import com.labmedical.backend.dtos.diets.PostRequestDietDTO;
import com.labmedical.backend.dtos.diets.PostResponseDietDTO;
import com.labmedical.backend.entities.Diet;
import com.labmedical.backend.entities.Patient;
import com.labmedical.backend.mappers.DietMapper;
import com.labmedical.backend.repositories.DietRepository;
import com.labmedical.backend.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DietServiceImpl implements DietService {

    @Autowired
    private DietRepository dietRepository;

    @Autowired
    private DietMapper dietMapper;


    @Autowired
    private PatientRepository patientRepository;

    public PostResponseDietDTO createDiet(PostRequestDietDTO postRequestDietDTO
            , Long patientId
    ){
        Optional<Patient> patientOptional = patientRepository.findById(patientId);

        if(patientOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        }
        Diet dietToSave = dietMapper.map(postRequestDietDTO);
        dietToSave.setSystemStatus(true);
        dietToSave.setPatient(patientOptional.get());

        return dietMapper
                .mapToPostResponseDietDTO(dietRepository.save(dietToSave));
    }

    @Override
    public PostResponseDietDTO updateDiet(Long id, PostRequestDietDTO postRequestDietDTO) {
        Optional<Diet> dietOptional = dietRepository.findById(id);
        if (dietOptional.isEmpty()) {
            throw new NoSuchElementException();
        }
        Long patientId = dietOptional.get().getPatient().getId();
        Optional<Patient> patientOptional = patientRepository.findById(patientId);

        if(patientOptional.isEmpty()){
            throw new NoSuchElementException();
        }
        Diet dietToUpdate = dietMapper.map(postRequestDietDTO);
        dietToUpdate.setPatient(patientOptional.get());

        dietToUpdate.setId(id);

        return dietMapper.mapToPostResponseDietDTO(dietRepository.save(dietToUpdate));}

    @Override
    public GetResponseDietDTO findDietById(Long id) {
        Optional<Diet> dietOptional = dietRepository.findById(id);
        if (dietOptional.isEmpty()) {
            throw new NoSuchElementException();
        }
        return dietMapper.mapToGetResponseDietDTO(dietOptional.get());
    }

    @Override
    public void deleteDietById(Long id) {
        Optional<Diet> dietOptional = dietRepository.findById(id);
        if (dietOptional.isEmpty()) {
            throw new NoSuchElementException();
        } else {
            dietRepository.delete(dietOptional.get());
        }
    }

    @Override
    public List<GetResponseDietDTO> findAllByName(String patientName) {
        if (patientName != null) {
            List<Diet> examList = dietRepository.findAllByPatientName(patientName);
            if (examList == null || examList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, patientName +
                        " has no exams");
            }
            return examList
                    .stream()
                    .map(dietMapper::mapToGetResponseDietDTO).toList();
        }
        return dietRepository.findAll()
                .stream()
                .map(dietMapper::mapToGetResponseDietDTO)
                .toList();
    }
}
