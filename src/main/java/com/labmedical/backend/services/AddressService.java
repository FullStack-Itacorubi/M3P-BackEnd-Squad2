package com.labmedical.backend.services;

import com.labmedical.backend.dtos.address.RequestAddressDTO;
import com.labmedical.backend.dtos.address.ResponseAddressDTO;
import com.labmedical.backend.entities.Address;
import com.labmedical.backend.mappers.AddressMapper;
import com.labmedical.backend.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressMapper addressMapper;

    public ResponseAddressDTO saveAddress(RequestAddressDTO address) {
        return addressMapper.mapAddressToResponseDTO(this.addressRepository.save(addressMapper.map(address)));
    }

    public ResponseAddressDTO findAddresById(Long addressId) {
        return this.addressMapper.mapAddressToResponseDTO(this.addressRepository.findById(addressId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found"))
        );
    }
}
