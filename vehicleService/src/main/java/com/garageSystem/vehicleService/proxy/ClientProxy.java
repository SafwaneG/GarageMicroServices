package com.garageSystem.vehicleService.proxy;


import com.garageSystem.vehicleService.model.dto.ClientResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "ClientService", url = "${client.url}")
public interface ClientProxy {
    @PostMapping("/Client/{identityNumber}")
    ClientResponseDto searchClient(@PathVariable  String identityNumber);
}
