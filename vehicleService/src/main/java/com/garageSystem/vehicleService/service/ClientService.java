package com.garageSystem.vehicleService.service;


import com.garageSystem.vehicleService.model.dto.ClientResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
@Service
public class ClientService {
    private final RestClient restClient;
    public  ClientService(@Value("${client.url}") String baseUrl){
            restClient=RestClient.builder()
                    .baseUrl(baseUrl)
                    .build();


    }
//    @GetMapping("/client/{identityNumber}")
    public ClientResponseDto searchClient( String identityNumber){
        return restClient.get()
                .uri("/client/{identityNumber}",identityNumber)
                .retrieve()
                .body(ClientResponseDto.class)
                ;

    }
}
