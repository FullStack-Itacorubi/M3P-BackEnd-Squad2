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
}