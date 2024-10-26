package com.garageSystem.vehicleService.model.entity;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "vehicle")
public class VehicleModel {
    @Id
    private String id;
    @NonNull
    private String ownerId;
     @NonNull
    private String chassisNumber;
     @NonNull
    private String registrationNumber;
     @NonNull
    private String brand;
     @NonNull
    private String model;
     @NonNull
    private Date yearOfManufacture;
     @NonNull
    private String color;
     @NonNull
    private int mileage;
     @NonNull
    private String fuelType;
     @NonNull
    private Date dateOfPurchase;
     @NonNull
    private VehicleCondition vehicleCondition;
}
