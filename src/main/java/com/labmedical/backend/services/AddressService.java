package com.labmedical.backend.services;

import com.labmedical.backend.entities.Address;
import com.labmedical.backend.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public void saveAddress(Address address){
       this.addressRepository.save(address);
    }
}
