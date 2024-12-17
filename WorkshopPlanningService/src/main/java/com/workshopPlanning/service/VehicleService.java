package com.workshopPlanning.service;

import com.workshopPlanning.model.VehicleCondition;
import com.workshopPlanning.model.dto.VehicleRequestUpdated;
import com.workshopPlanning.model.dto.VehicleResponse;
import com.workshopPlanning.model.dto.VehicleResponseDtoList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    public VehicleResponse updateCondition(String registrationNumber, VehicleCondition vehicleCondition){
        return restClient.put()
                .uri("/updateCondition")
                .contentType(MediaType.APPLICATION_JSON)
                .body(new VehicleRequestUpdated(registrationNumber, vehicleCondition))
                .retrieve()
                .body(VehicleResponse.class);
    }

    }


