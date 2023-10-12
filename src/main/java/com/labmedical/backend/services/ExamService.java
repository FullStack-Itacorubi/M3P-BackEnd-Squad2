package com.labmedical.backend.services;

import com.labmedical.backend.dtos.exams.PostRequestExamDTO;
import com.labmedical.backend.dtos.exams.PostResponseExamDTO;
import com.labmedical.backend.entities.Exam;
import org.springframework.stereotype.Service;

public interface ExamService {

    PostResponseExamDTO createExam(PostRequestExamDTO postRequestExamDTO);

    PostResponseExamDTO updateExam(Long id, PostRequestExamDTO postRequestExamDTO);

}
