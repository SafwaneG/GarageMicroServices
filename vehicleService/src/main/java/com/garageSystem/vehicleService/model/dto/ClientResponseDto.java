package com.garageSystem.vehicleService.model.dto;

import com.garageSystem.vehicleService.model.Address;

public record ClientResponseDto(
        String id,
        String firstName,
        String lastName,
        String phoneNumber,
        String email,
        Address address
        ){}