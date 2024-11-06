package com.garageSystem.vehicleService.service;

import com.garageSystem.vehicleService.model.dto.ClientResponseDto;
import com.garageSystem.vehicleService.model.dto.RequestVehicleDTO;
import com.garageSystem.vehicleService.model.dto.ResponseVehicleDTO;
import com.garageSystem.vehicleService.model.dto.ResponseVehicleDtoList;
import com.garageSystem.vehicleService.model.entity.VehicleModel;
import com.garageSystem.vehicleService.repository.VehicleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleService {
    private final VehicleRepo vehicleRepo;
    private final ClientService clientService;

    public ResponseVehicleDTO createVehicle(RequestVehicleDTO requestVehicle) {
        ClientResponseDto  client = clientService.searchClient(requestVehicle.ownerIdentityNumber());
        if (client != null) {
            VehicleModel vehicle = new VehicleModel(requestVehicle.ownerIdentityNumber(), requestVehicle.chassisNumber(), requestVehicle.registrationNumber(), requestVehicle.brand(), requestVehicle.model(), requestVehicle.yearOfManufacture(), requestVehicle.color(), requestVehicle.mileage(), requestVehicle.fuelType(), requestVehicle.dateOfPurchase(), requestVehicle.vehicleCondition());
            VehicleModel vehicleCreated = vehicleRepo.save(vehicle);
            System.out.println("creted: "+vehicleCreated);
            return new ResponseVehicleDTO(client.firstName(), client.email(), vehicleCreated.getRegistrationNumber(),  vehicleCreated.getVehicleCondition());
        }
        return null;
    }

        public List<ResponseVehicleDTO> getAllVehicles() {
            return vehicleRepo.findAll().stream().map(vehicle -> {
                ClientResponseDto client = clientService.searchClient(vehicle.getOwnerId());
                return new ResponseVehicleDTO(client.firstName(), client.email(), vehicle.getRegistrationNumber(), vehicle.getVehicleCondition());
            }).toList();

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
}
