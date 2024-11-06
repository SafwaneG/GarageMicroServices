package com.workshopPlanning.service;

import com.workshopPlanning.model.dto.VehicleResponse;
import com.workshopPlanning.model.dto.VehicleResponseDtoList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

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
    public VehicleResponseDtoList getVehiclesByRegistrationNumbers(List<String> registrationNumbers) {
        return restClient.post()
                .uri("/someVehicles")
                .contentType(MediaType.APPLICATION_JSON)
                .body(registrationNumbers)
                .retrieve()
                .body(VehicleResponseDtoList.class);
    }
    }


