package com.garageSystem.vehicleService.model.dto;

import com.garageSystem.vehicleService.model.entity.VehicleCondition;

import java.util.Date;

public record ResponseVehicleDTO(String name,String email,String registrationNumber, VehicleCondition vehicleCondition) {
}
