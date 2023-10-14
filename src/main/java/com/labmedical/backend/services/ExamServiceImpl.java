package com.labmedical.backend.services;

import com.labmedical.backend.dtos.exams.GetResponseExamDTO;
import com.labmedical.backend.dtos.exams.PostRequestExamDTO;
import com.labmedical.backend.dtos.exams.PostResponseExamDTO;
import com.labmedical.backend.entities.Exam;
import com.labmedical.backend.mappers.ExamMapper;
import com.labmedical.backend.repositories.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ExamServiceImpl implements ExamService {

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

    @Override
    public PostResponseExamDTO updateExam(Long id, PostRequestExamDTO postRequestExamDTO) {
        Optional<Exam> examOptional = examRepository.findById(id);
        if (examOptional.isEmpty()) {
            throw new NoSuchElementException();
        }

        Exam examToUpdate = examMapper.map(postRequestExamDTO);
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
}
