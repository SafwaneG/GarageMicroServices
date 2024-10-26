package com.garageSystem.vehicleService.model.dto;

import com.garageSystem.vehicleService.model.entity.VehicleCondition;

import java.util.Date;
public record RequestVehicleDTO(String ownerId, String chassisNumber, String registrationNumber,String brand, String model, Date yearOfManufacture, String color, int mileage, String fuelType, Date dateOfPurchase, VehicleCondition vehicleCondition) {

}
