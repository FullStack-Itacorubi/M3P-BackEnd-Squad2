package com.labmedical.backend.dtos.address;

public record RequestAddressDTO(

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