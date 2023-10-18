package com.labmedical.backend.services;

import com.labmedical.backend.dtos.exams.GetResponseExamDTO;
import com.labmedical.backend.dtos.exams.PostRequestExamDTO;
import com.labmedical.backend.dtos.exams.PostResponseExamDTO;

import java.util.List;

public interface ExamService {

    PostResponseExamDTO createExam(PostRequestExamDTO postRequestExamDTO, Long patientId);

    PostResponseExamDTO updateExam(Long id, PostRequestExamDTO postRequestExamDTO);

    GetResponseExamDTO findExamById(Long id);

    void deleteExamById(Long id);

    List<GetResponseExamDTO> findAllByName(String patientName);
}
