package com.labmedical.backend.mappers;

import com.labmedical.backend.dtos.exams.RequestExamDTO;
import com.labmedical.backend.dtos.exams.ResponseExamDTO;
import com.labmedical.backend.entities.Exam;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ExamMapper {

    Exam map(RequestExamDTO source);

    Exam map(ResponseExamDTO source);

    RequestExamDTO mapToRequestExamDTO(Exam source);

    ResponseExamDTO mapToResponseExamDTO(Exam source);
}
