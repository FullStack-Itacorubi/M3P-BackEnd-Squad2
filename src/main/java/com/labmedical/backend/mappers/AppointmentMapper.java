package com.labmedical.backend.mappers;

import com.labmedical.backend.dtos.appointment.AppointmentRequestDTO;
import com.labmedical.backend.dtos.appointment.AppointmentResponseDTO;
import com.labmedical.backend.entities.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    AppointmentMapper INSTANCE = Mappers.getMapper(AppointmentMapper.class);
    Appointment toEntity(AppointmentRequestDTO requestDTO);

    AppointmentResponseDTO toDTO(Appointment appointment);

    List<AppointmentResponseDTO> toDTOList(List<Appointment> appointments);
}

