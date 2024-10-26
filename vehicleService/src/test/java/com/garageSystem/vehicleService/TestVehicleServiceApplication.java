package com.garageSystem.vehicleService;

import org.springframework.boot.SpringApplication;

public class TestVehicleServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(VehicleServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
