package com.garageSystem.vehicleService.repository;
import com.garageSystem.vehicleService.model.entity.VehicleModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepo extends MongoRepository<VehicleModel, String> {
    public VehicleModel findByRegistrationNumber(String registrationNumber);

}
