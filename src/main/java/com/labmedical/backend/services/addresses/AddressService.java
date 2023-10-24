package com.labmedical.backend.services.addresses;

import com.labmedical.backend.dtos.address.RequestAddressDTO;
import com.labmedical.backend.dtos.address.ResponseAddressDTO;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {

    ResponseAddressDTO saveAddress(RequestAddressDTO address);

    ResponseAddressDTO findAddresById(Long addressId);
}
