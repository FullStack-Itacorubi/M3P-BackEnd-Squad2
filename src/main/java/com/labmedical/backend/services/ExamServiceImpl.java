package com.labmedical.backend.services;

import com.labmedical.backend.dtos.exams.PostRequestExamDTO;
import com.labmedical.backend.dtos.exams.PostResponseExamDTO;
import com.labmedical.backend.entities.Exam;
import com.labmedical.backend.mappers.ExamMapper;
import com.labmedical.backend.repositories.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamServiceImpl implements ExamService{

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ExamMapper examMapper;


    @Override
    public PostResponseExamDTO createExam(PostRequestExamDTO postRequestExamDTO) {
        Exam examToSave = examMapper.map(postRequestExamDTO);
        return examMapper
                .mapExamToPostResponseExamDTO(examRepository.save(examToSave));

    }
}
