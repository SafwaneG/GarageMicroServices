package com.garageSystem.vehicleService.controller;

import com.garageSystem.vehicleService.model.dto.RequestVehicleDTO;
import com.garageSystem.vehicleService.model.dto.ResponseVehicleDTO;
import com.garageSystem.vehicleService.model.dto.ResponseVehicleDtoList;
import com.garageSystem.vehicleService.model.entity.VehicleCondition;
import com.garageSystem.vehicleService.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/vehicle")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;


    @PostMapping
    public ResponseVehicleDTO createVehicle(@RequestBody RequestVehicleDTO requestVehicle){
           return vehicleService.createVehicle(requestVehicle);

    }
    @GetMapping
    public List<ResponseVehicleDTO> getAllVehicles(){
        return vehicleService.getAllVehicles();
    }
    @GetMapping("/{registrationNumber}")
    public ResponseVehicleDTO getVehicle(@PathVariable("registrationNumber") String registrationNumber){
        return vehicleService.getVehicle(registrationNumber);
    }
    @PostMapping("/someVehicles")
    public ResponseVehicleDtoList getSomeVehicles(@RequestBody List<String> registrationNumbers) {
        System.out.println("netrypoint");
       return vehicleService.getSomeVehicles(registrationNumbers);

    }
    @GetMapping("/registrationNumbers")
    public  List<String> getAllRegistrationsNumbers(){
        return vehicleService.getAllRegistrationsNumbers();
    }
    @GetMapping("/conditions")
    public List<String> getAllStatusWorkshop() {
        return Arrays.stream(VehicleCondition.values())
                .map(Enum::name)
                .toList();
    }
}

