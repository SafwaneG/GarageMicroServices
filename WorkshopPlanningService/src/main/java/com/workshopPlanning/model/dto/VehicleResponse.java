package com.workshopPlanning.model.dto;

import com.workshopPlanning.model.VehicleCondition;

public record VehicleResponse(String name,String email,String registrationNumber, VehicleCondition vehicleCondition) {
}


