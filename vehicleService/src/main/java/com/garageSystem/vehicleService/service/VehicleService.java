package com.garageSystem.vehicleService.service;

import com.garageSystem.vehicleService.model.dto.ClientResponseDto;
import com.garageSystem.vehicleService.model.dto.RequestVehicleDTO;
import com.garageSystem.vehicleService.model.dto.ResponseVehicleDTO;
import com.garageSystem.vehicleService.model.entity.VehicleModel;
import com.garageSystem.vehicleService.repository.VehicleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepo vehicleRepo;
    private final ClientService clientService;
   private ClientResponseDto client;
    public ResponseVehicleDTO createVehicle(RequestVehicleDTO requestVehicle) {
   client = clientService.searchClient(requestVehicle.ownerIdentityNumber());
        System.out.println(client);
        if (client != null) {
            VehicleModel vehicle = new VehicleModel(client.id(), requestVehicle.chassisNumber(), requestVehicle.registrationNumber(), requestVehicle.brand(), requestVehicle.model(), requestVehicle.yearOfManufacture(), requestVehicle.color(), requestVehicle.mileage(), requestVehicle.fuelType(), requestVehicle.dateOfPurchase(), requestVehicle.vehicleCondition());
            VehicleModel vehicleCreated = vehicleRepo.save(vehicle);
            return new ResponseVehicleDTO(client.firstName(), client.email(), vehicleCreated.getRegistrationNumber(),  vehicleCreated.getVehicleCondition());
        }
        return null;
    }

        public List<ResponseVehicleDTO> getAllVehicles() {
        return vehicleRepo.findAll().stream().map(
                vehicle->new ResponseVehicleDTO(client.firstName(),client.email(),vehicle.getRegistrationNumber(),vehicle.getVehicleCondition()))
                .toList();

    }

    public ResponseVehicleDTO getVehicle(String registrationNumber) {
        VehicleModel vehicle=vehicleRepo.findByRegistrationNumber(registrationNumber);
        return new ResponseVehicleDTO(client.firstName(),client.email(),vehicle.getRegistrationNumber(),vehicle.getVehicleCondition());
    }
}
