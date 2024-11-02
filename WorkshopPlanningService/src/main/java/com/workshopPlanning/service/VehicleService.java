package com.workshopPlanning.service;

import com.workshopPlanning.model.dto.VehicleResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
@Service
public class VehicleService {
        private final RestClient restClient;
        public  VehicleService(@Value("${vehicle.url}") String baseUrl){
            restClient=RestClient.builder()
                    .baseUrl(baseUrl)
                    .build();


        }
        public VehicleResponse searchVehicle(String registrationNumber){
            return restClient.get()
                    .uri("/{registrationNumber}",registrationNumber)
                    .retrieve()
                    .body(VehicleResponse.class)
                    ;

        }
    }


