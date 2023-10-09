package com.labmedical.backend.mappers;

import com.labmedical.backend.dtos.address.RequestAddressDTO;
import com.labmedical.backend.dtos.address.ResponseAddressDTO;
import com.labmedical.backend.entities.Address;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AddressMapper {

    Address map(ResponseAddressDTO source);

    Address map(RequestAddressDTO source);

    ResponseAddressDTO mapAddressToResponseDTO(Address source);

    RequestAddressDTO mapAddressToRequestDTO(Address source);

}
