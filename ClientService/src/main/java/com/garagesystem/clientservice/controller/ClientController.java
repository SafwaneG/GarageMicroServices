package com.garagesystem.clientservice.controller;

import com.garagesystem.clientservice.model.dto.ClientDto;
import com.garagesystem.clientservice.model.dto.ClientResponseDto;
import com.garagesystem.clientservice.service.ClientService;
import jakarta.websocket.server.PathParam;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/client")

public class ClientController {
    private final ClientService clientService;

    @PostMapping
    public ClientResponseDto createClient(@RequestBody ClientDto clientDto) {
        System.out.println("in controller");
        return clientService.createClient(clientDto);
    }
    @GetMapping
    public List<ClientResponseDto> getAllClients() {
        return clientService.getAllClients();
    }
@GetMapping("/identityNumber")
public  List<String> getAllIdentityNumber(){
        return clientService.getAllIdentityNumber();
}
    @GetMapping("/{identityNumber}")
    public ClientResponseDto getClient(@PathVariable String identityNumber) {
        System.out.println("client got called");
        return clientService.getClient(identityNumber);
    }

//    @PutMapping
//    public ClientDto updateClient(@RequestBody ClientDto clientDto){
//        return clientService.updateClient(clientDto);
//    }


}
