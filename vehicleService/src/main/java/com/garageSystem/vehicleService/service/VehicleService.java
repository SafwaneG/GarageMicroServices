package com.garageSystem.vehicleService.service;

import com.garageSystem.vehicleService.model.dto.*;
import com.garageSystem.vehicleService.model.entity.VehicleCondition;
import com.garageSystem.vehicleService.model.entity.VehicleModel;
import com.garageSystem.vehicleService.repository.VehicleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepo vehicleRepo;
    private final ClientService clientService;
    private ClientResponseDto client;

    public VehicleModel createVehicle(RequestVehicleDTO requestVehicle) {
        ClientResponseDto  client = clientService.searchClient(requestVehicle.ownerIdentityNumber());
        if (client != null) {
            VehicleModel vehicle = new VehicleModel(requestVehicle.ownerIdentityNumber(), requestVehicle.chassisNumber(), requestVehicle.registrationNumber(), requestVehicle.brand(), requestVehicle.model(), requestVehicle.yearOfManufacture(), requestVehicle.color(), requestVehicle.mileage(), requestVehicle.fuelType(), requestVehicle.dateOfPurchase(), requestVehicle.vehicleCondition());
            VehicleModel vehicleCreated = vehicleRepo.save(vehicle);
            System.out.println("creted: "+vehicleCreated);
            return vehicleCreated;
        }
        return null;
    }

    public List<VehicleModel> getAllVehicles() {
        return vehicleRepo.findAll().stream().map(
                        vehicle -> vehicle)
                .toList();

    }


    public ResponseVehicleDTO getVehicle(String registrationNumber) {
        System.out.println("re"+registrationNumber);

        VehicleModel vehicle=vehicleRepo.findByRegistrationNumber(registrationNumber);
        System.out.println("v"+vehicle);
        if (vehicle == null) {
            throw new RuntimeException("Error: Vehicle not found for registration number this spec: " + registrationNumber);
        }
        ClientResponseDto  client = clientService.searchClient(vehicle.getOwnerId());
        return new ResponseVehicleDTO(client.firstName(),client.email(),vehicle.getRegistrationNumber(),vehicle.getVehicleCondition());
    }
    public ResponseVehicleDtoList getSomeVehicles(List<String> registrationNumbers){
        System.out.println(registrationNumbers);
        List<ResponseVehicleDTO> vehicles=new ArrayList<>();

        for(String registrationNumber : registrationNumbers){
            VehicleModel vehicle= vehicleRepo.findByRegistrationNumber(registrationNumber);
            System.out.println(vehicle);
            if (vehicle == null) {
                throw new RuntimeException("Error: Vehicle not found for registration number: " + registrationNumber);
            }
            System.out.println("av");
            ClientResponseDto  client = clientService.searchClient(vehicle.getOwnerId());
            System.out.println("ap");
            vehicles.add(new ResponseVehicleDTO(client.firstName(),client.email(),vehicle.getRegistrationNumber(),vehicle.getVehicleCondition()));
       }
        return  new ResponseVehicleDtoList(vehicles);
    }

    public List<String> getAllRegistrationsNumbers() {
            return vehicleRepo.findAll()
                    .stream()
                    .map(VehicleModel::getRegistrationNumber)
                    .collect(Collectors.toList());
        }
    public VehicleModel modifyVehicle(VehicleModel vehicleModel) {
        // Trouver le véhicule existant
        VehicleModel existingVehicle = vehicleRepo.findByRegistrationNumber(vehicleModel.getRegistrationNumber());
        if (existingVehicle == null) {
            throw new RuntimeException("Error: Vehicle not found for registration number: " + vehicleModel.getRegistrationNumber());
        }
        if (vehicleModel.getColor() != null) {
            existingVehicle.setColor(vehicleModel.getColor());
        }
        if (vehicleModel.getMileage() != 0) {
            existingVehicle.setMileage(vehicleModel.getMileage());
        }

        return vehicleRepo.save(existingVehicle);
    }
    public ResponseVehicleDTO updateVehicleCondition(String registrationNumber, VehicleCondition newCondition) {
        // Trouver le véhicule existant
        VehicleModel existingVehicle = vehicleRepo.findByRegistrationNumber(registrationNumber);
        if (existingVehicle == null) {
            throw new RuntimeException("Error: Vehicle not found for registration number: " + registrationNumber);
        }

        // Mettre à jour uniquement l'état du véhicule
        if (newCondition != null ) {
            existingVehicle.setVehicleCondition(newCondition);
        } else {
            throw new IllegalArgumentException("Error: Vehicle condition cannot be null or empty.");
        }

        // Enregistrer la modification
        VehicleModel vehicleUpdated=vehicleRepo.save(existingVehicle);
        ClientResponseDto  client = clientService.searchClient(vehicleUpdated.getOwnerId());
        return new ResponseVehicleDTO(client.firstName(),client.email(),vehicleUpdated.getRegistrationNumber(),vehicleUpdated.getVehicleCondition());
    }



}

