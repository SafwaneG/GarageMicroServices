package com.garagesystem.clientservice.model.dto;

import com.garagesystem.clientservice.model.entity.Address;

public record ClientDto(
        String identityNumber,
        String firstName,
        String lastName,
        String phoneNumber,
        String email,
        Address address
) { }
