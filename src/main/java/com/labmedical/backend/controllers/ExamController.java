package com.labmedical.backend.controllers;

import com.labmedical.backend.dtos.exams.GetResponseExamDTO;
import com.labmedical.backend.dtos.exams.PostRequestExamDTO;
import com.labmedical.backend.dtos.exams.PostResponseExamDTO;
import com.labmedical.backend.mappers.ExamMapper;
import com.labmedical.backend.services.ExamServiceImpl;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/exames")
public class ExamController {

    @Autowired
    private ExamServiceImpl examService;

    @Autowired
    private ExamMapper examMapper;

    @PostMapping
    public ResponseEntity<PostResponseExamDTO> createExam(
            @Validated @RequestBody PostRequestExamDTO postRequestExamDTO,
            @RequestParam Long patientId
    ) {
        try {
            return new ResponseEntity<>(examService.createExam(postRequestExamDTO, patientId), HttpStatus.CREATED);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Error");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostResponseExamDTO> updateExam(
            @PathVariable Long id,
            @Validated @RequestBody PostRequestExamDTO postRequestExamDTO) {
        return new ResponseEntity<>(examService.updateExam(id, postRequestExamDTO), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<GetResponseExamDTO> getExamById(@PathVariable Long id){
        return new ResponseEntity<>(examService.findExamById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteExamById(@PathVariable Long id){

        examService.deleteExamById(id);

    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errorMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                .toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        Throwable mostSpecificCause = ex.getMostSpecificCause();

        String errorMessage = mostSpecificCause.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex) {
        Throwable errorCause = ex.getCause();
        if (errorCause != null) {
            String errorMessage = errorCause.getMessage();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Exam not found at the database");
    }

}