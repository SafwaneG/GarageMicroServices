package com.garageSystem.vehicleService.service;

import com.garageSystem.vehicleService.model.dto.ClientResponseDto;
import com.garageSystem.vehicleService.model.dto.RequestVehicleDTO;
import com.garageSystem.vehicleService.model.dto.ResponseVehicleDTO;
import com.garageSystem.vehicleService.model.entity.VehicleModel;
import com.garageSystem.vehicleService.proxy.ClientProxy;
import com.garageSystem.vehicleService.repository.VehicleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepo vehicleRepo;

    private final ClientProxy clientProxy;
    public ResponseVehicleDTO createVehicle(RequestVehicleDTO requestVehicle) {
        ClientResponseDto client = clientProxy.searchClient(requestVehicle.ownerIdentityNumber());
        System.out.println(client);
        if (client != null) {
            VehicleModel vehicle = new VehicleModel(client.id(), requestVehicle.chassisNumber(), requestVehicle.registrationNumber(), requestVehicle.brand(), requestVehicle.model(), requestVehicle.yearOfManufacture(), requestVehicle.color(), requestVehicle.mileage(), requestVehicle.fuelType(), requestVehicle.dateOfPurchase(), requestVehicle.vehicleCondition());
            VehicleModel vehicleCreated = vehicleRepo.save(vehicle);
            return new ResponseVehicleDTO(vehicleCreated.getId(), client.id(), vehicleCreated.getChassisNumber(), vehicleCreated.getRegistrationNumber(), vehicleCreated.getBrand(), vehicleCreated.getModel(), vehicleCreated.getYearOfManufacture(), vehicleCreated.getColor(), vehicleCreated.getMileage(), vehicleCreated.getFuelType(), vehicleCreated.getDateOfPurchase(), vehicleCreated.getVehicleCondition());
        }
        return null;
    }

        public List<ResponseVehicleDTO> getAllVehicles() {
        return vehicleRepo.findAll().stream().map(
                vehicle->new ResponseVehicleDTO(vehicle.getId(),vehicle.getOwnerId(),vehicle.getChassisNumber(),vehicle.getRegistrationNumber(),vehicle.getBrand(),vehicle.getModel(),vehicle.getYearOfManufacture(),vehicle.getColor(),vehicle.getMileage(),vehicle.getFuelType(),vehicle.getDateOfPurchase(),vehicle.getVehicleCondition()))
                .toList();

    }

    public ResponseVehicleDTO getVehicle(String registrationNumber) {
        VehicleModel vehicle=vehicleRepo.findByRegistrationNumber(registrationNumber);
        return new ResponseVehicleDTO(vehicle.getId(),vehicle.getOwnerId(),vehicle.getChassisNumber(),vehicle.getRegistrationNumber(),vehicle.getBrand(),vehicle.getModel(),vehicle.getYearOfManufacture(),vehicle.getColor(),vehicle.getMileage(),vehicle.getFuelType(),vehicle.getDateOfPurchase(),vehicle.getVehicleCondition());
    }
}
