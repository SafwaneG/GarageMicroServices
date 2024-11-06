package com.garageSystem.vehicleService.model.entity;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
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
    @Indexed(unique = true)
    private String ownerId;
     @NonNull
     @Indexed(unique = true)
    private String chassisNumber;
     @NonNull
     @Indexed(unique = true)
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

    public VehicleModel(@NonNull String ownerId, @NonNull String chassisNumber, @NonNull String registrationNumber, @NonNull String brand, @NonNull String model, @NonNull Date yearOfManufacture, @NonNull String color, int mileage, @NonNull String fuelType, @NonNull Date dateOfPurchase, @NonNull VehicleCondition vehicleCondition) {
        this.ownerId = ownerId;
        this.chassisNumber = chassisNumber;
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.model = model;
        this.yearOfManufacture = yearOfManufacture;
        this.color = color;
        this.mileage = mileage;
        this.fuelType = fuelType;
        this.dateOfPurchase = dateOfPurchase;
        this.vehicleCondition = vehicleCondition;
    }
}
