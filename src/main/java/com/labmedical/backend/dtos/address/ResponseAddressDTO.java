package com.labmedical.backend.dtos.address;

public record ResponseAddressDTO(
        Long id,

        String cep,

        String city,

        String state,

        String street,

        String number,

        String complement,

        String district,

        String reference
) {
//
//    public ResponseAddressDTO(Address address) {
//        this(
//                address.getCep(),
//                address.getCity(),
//                address.getState(),
//                address.getStreet(),
//                address.getNumber(),
//                address.getComplement(),
//                address.getDistrict(),
//                address.getReference()
//        );
//    }
}