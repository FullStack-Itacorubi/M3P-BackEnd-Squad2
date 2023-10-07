package com.labmedical.backend.services;

import com.labmedical.backend.dtos.address.ResponseAddressDTO;
import com.labmedical.backend.entities.Address;
import com.labmedical.backend.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public void saveAddress(Address address){
       this.addressRepository.save(address);
    }

    public ResponseAddressDTO findAddresById(Long addressId) {
        return new ResponseAddressDTO(this.addressRepository.findById(addressId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found"))
        );
    }
}
