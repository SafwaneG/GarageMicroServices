package com.garageSystem.vehicleService.model.dto;

import com.garageSystem.vehicleService.model.entity.VehicleCondition;

public record VehicleRequestUpdated (String registrationNumber, VehicleCondition vehicleCondition){
}
