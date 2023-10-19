package com.labmedical.backend.services;

import com.labmedical.backend.dtos.exams.GetResponseExamDTO;
import com.labmedical.backend.dtos.exams.PostRequestExamDTO;
import com.labmedical.backend.dtos.exams.PostResponseExamDTO;
import com.labmedical.backend.entities.Exam;
import com.labmedical.backend.entities.Patient;
import com.labmedical.backend.mappers.ExamMapper;
import com.labmedical.backend.repositories.ExamRepository;
import com.labmedical.backend.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ExamMapper examMapper;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public PostResponseExamDTO createExam(PostRequestExamDTO postRequestExamDTO, Long patientId) {
        Optional<Patient> patientOptional = patientRepository.findById(patientId);

        if(patientOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        }
        Exam examToSave = examMapper.map(postRequestExamDTO);
        examToSave.setPatient(patientOptional.get());

        return examMapper
                .mapExamToPostResponseExamDTO(examRepository.save(examToSave));

    }

    @Override
    public PostResponseExamDTO updateExam(Long id, PostRequestExamDTO postRequestExamDTO) {
        Optional<Exam> examOptional = examRepository.findById(id);
        if (examOptional.isEmpty()) {
            throw new NoSuchElementException();
        }
        Long patientId = examOptional.get().getPatient().getId();
        Optional<Patient> patientOptional = patientRepository.findById(patientId);

        if(patientOptional.isEmpty()){
            throw new NoSuchElementException();
        }
        Exam examToUpdate = examMapper.map(postRequestExamDTO);
        examToUpdate.setPatient(patientOptional.get());

        examToUpdate.setId(id);

        return examMapper.mapExamToPostResponseExamDTO(examRepository.save(examToUpdate));
    }

    @Override
    public GetResponseExamDTO findExamById(Long id) {
        Optional<Exam> examOptional = examRepository.findById(id);
        if (examOptional.isEmpty()) {
            throw new NoSuchElementException();
        }
        return examMapper.mapExamToGetResponseExamDTO(examOptional.get());

    }

    @Override
    public void deleteExamById(Long id) {
        Optional<Exam> examOptional = examRepository.findById(id);
        if (examOptional.isEmpty()) {
            throw new NoSuchElementException();
        }else{
            examRepository.delete(examOptional.get());
        }
    }

    @Override
    public List<GetResponseExamDTO> findAllByName(String patientName) {
        if (patientName != null) {
            List<Exam> examList = examRepository.findAllByPatientName(patientName);
            if (examList == null || examList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, patientName +
                        " has no exams");
            }
            return examList
                    .stream()
                    .map(examMapper::mapExamToGetResponseExamDTO).toList();
        }
        return examRepository.findAll()
                .stream()
                .map(examMapper::mapExamToGetResponseExamDTO)
                .toList();
    }
}