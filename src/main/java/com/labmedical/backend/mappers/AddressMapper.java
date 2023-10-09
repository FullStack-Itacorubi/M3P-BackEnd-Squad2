package com.labmedical.backend.mappers;

import com.labmedical.backend.dtos.address.ResponseAddressDTO;
import com.labmedical.backend.entities.Address;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AddressMapper {

    Address map(ResponseAddressDTO source);

    ResponseAddressDTO mapAddressToResponseDTO(Address source);

}
