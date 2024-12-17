package com.workshopPlanning.model.dto;


import com.workshopPlanning.model.VehicleCondition;

public record VehicleRequestUpdated(String registrationNumber, VehicleCondition vehicleCondition){
}
