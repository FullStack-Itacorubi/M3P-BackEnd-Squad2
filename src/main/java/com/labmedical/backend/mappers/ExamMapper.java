package com.labmedical.backend.mappers;

import com.labmedical.backend.dtos.address.RequestAddressDTO;
import com.labmedical.backend.dtos.address.ResponseAddressDTO;
import com.labmedical.backend.dtos.exams.GetResponseExamDTO;
import com.labmedical.backend.dtos.exams.PostRequestExamDTO;
import com.labmedical.backend.dtos.exams.PostResponseExamDTO;
import com.labmedical.backend.entities.Address;
import com.labmedical.backend.entities.Exam;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ExamMapper {

    Exam map(PostRequestExamDTO source);

    Exam map(PostResponseExamDTO source);

    Exam map(GetResponseExamDTO source);

    PostRequestExamDTO mapExamToPostRequestExamDTO(Exam source);

    PostResponseExamDTO mapExamToPostResponseExamDTO(Exam source);

    GetResponseExamDTO mapExamToGetResponseExamDTO(Exam source);

}
