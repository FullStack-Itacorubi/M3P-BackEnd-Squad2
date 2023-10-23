package com.labmedical.backend.mappers;

import com.labmedical.backend.dtos.Users.UpdateUsersRequestDTO;
import com.labmedical.backend.dtos.appointment.AppointmentRequestDTO;
import com.labmedical.backend.dtos.appointment.AppointmentResponseDTO;
import com.labmedical.backend.dtos.appointment.UpdateAppointmentRequestDTO;
import com.labmedical.backend.entities.Appointment;
import com.labmedical.backend.entities.Users;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    AppointmentMapper INSTANCE = Mappers.getMapper(AppointmentMapper.class);
    Appointment toEntity(AppointmentRequestDTO requestDTO);
    AppointmentResponseDTO toDTO(Appointment appointment);
    List<AppointmentResponseDTO> toDTOList(List<Appointment> appointments);

    @Mapping(target = "id", ignore = true)
    void updateAppointmentRequestDTOToAppointment(
            UpdateAppointmentRequestDTO dto,
            @MappingTarget Appointment existingAppointment
    );

    @AfterMapping
    default void afterUpdateAppointmentRequestDTOToAppointment(
            UpdateAppointmentRequestDTO dto,
            @MappingTarget Appointment updatedAppointment,
            @Context Appointment existingAppointment
    ) {
        if (dto.medicationPrescribed() == null) {
            updatedAppointment.setMedicationPrescribed(existingAppointment.getMedicationPrescribed());
        }
    }
}

