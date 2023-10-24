package com.labmedical.backend.services.exams;

import com.labmedical.backend.dtos.exams.RequestExamDTO;
import com.labmedical.backend.dtos.exams.ResponseExamDTO;

import java.util.List;

public interface ExamService {

    ResponseExamDTO createExam(RequestExamDTO requestExamDTO, Long patientId);

    ResponseExamDTO updateExam(Long id, RequestExamDTO requestExamDTO);

    ResponseExamDTO findExamById(Long id);

    void deleteExamById(Long id);

    List<ResponseExamDTO> findAllByName(String patientName);
}
