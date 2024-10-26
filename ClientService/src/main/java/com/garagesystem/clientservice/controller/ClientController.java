package com.garagesystem.clientservice.controller;

import com.garagesystem.clientservice.model.dto.ClientDto;
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
    public ClientDto createClient(@RequestBody ClientDto clientDto) {
        return clientService.createClient(clientDto);
    }
    @GetMapping
    public List<ClientDto> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{identityNumber}")
    public ClientDto getClient(@PathVariable String identityNumber) {
        return clientService.getClient(identityNumber);
    }

//    @PutMapping
//    public ClientDto updateClient(@RequestBody ClientDto clientDto){
//        return clientService.updateClient(clientDto);
//    }


}
