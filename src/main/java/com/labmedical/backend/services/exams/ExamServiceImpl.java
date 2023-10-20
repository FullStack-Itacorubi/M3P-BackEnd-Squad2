package com.labmedical.backend.services.exams;

import com.labmedical.backend.dtos.exams.RequestExamDTO;
import com.labmedical.backend.dtos.exams.ResponseExamDTO;
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
    public ResponseExamDTO createExam(RequestExamDTO requestExamDTO, Long patientId) {
        Optional<Patient> patientOptional = patientRepository.findById(patientId);

        if(patientOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        }
        Exam examToSave = examMapper.map(requestExamDTO);
        examToSave.setPatient(patientOptional.get());

        return examMapper
                .mapToResponseExamDTO(examRepository.save(examToSave));

    }

    @Override
    public ResponseExamDTO updateExam(Long id, RequestExamDTO requestExamDTO) {
        Optional<Exam> examOptional = examRepository.findById(id);
        if (examOptional.isEmpty()) {
            throw new NoSuchElementException();
        }
        Long patientId = examOptional.get().getPatient().getId();
        Optional<Patient> patientOptional = patientRepository.findById(patientId);

        if(patientOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient not found");
        }
        Exam examToUpdate = examMapper.map(requestExamDTO);
        examToUpdate.setPatient(patientOptional.get());

        examToUpdate.setId(id);

        return examMapper.mapToResponseExamDTO(examRepository.save(examToUpdate));
    }

    @Override
    public ResponseExamDTO findExamById(Long id) {
        Optional<Exam> examOptional = examRepository.findById(id);
        if (examOptional.isEmpty()) {
            throw new NoSuchElementException();
        }
        return examMapper.mapToResponseExamDTO(examOptional.get());

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
    public List<ResponseExamDTO> findAllByName(String patientName) {
        if (patientName != null) {
            List<Exam> examList = examRepository.findAllByPatientName(patientName);
            if (examList == null || examList.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, patientName +
                        " has no exams");
            }
            return examList
                    .stream()
                    .map(examMapper::mapToResponseExamDTO).toList();
        }
        return examRepository.findAll()
                .stream()
                .map(examMapper::mapToResponseExamDTO)
                .toList();
    }
}