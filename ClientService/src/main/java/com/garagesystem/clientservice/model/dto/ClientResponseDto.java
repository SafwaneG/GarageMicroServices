package com.garagesystem.clientservice.model.dto;

import com.garagesystem.clientservice.model.entity.Address;

public record ClientResponseDto(

        String id,
        String identityNumber,
        String firstName,
        String lastName,
        String phoneNumber,
        String email,
        Address address) {}
