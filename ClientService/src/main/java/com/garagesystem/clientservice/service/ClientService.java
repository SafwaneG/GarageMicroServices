package com.garagesystem.clientservice.service;

import com.garagesystem.clientservice.exception.ClientNotFoundException;
import com.garagesystem.clientservice.mappers.ClientMapper;
import com.garagesystem.clientservice.model.dto.ClientDto;
import com.garagesystem.clientservice.model.dto.ClientResponseDto;
import com.garagesystem.clientservice.model.entity.Client;
import com.garagesystem.clientservice.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientResponseDto createClient(ClientDto clientDto) {
        Client client = clientMapper.toEntity(clientDto);
        Client createdClient = clientRepository.save(client);
        return clientMapper.apply(createdClient);
    }
    public List<ClientResponseDto> getAllClients() {
        return clientRepository.findAll().stream().map(clientMapper).collect(Collectors.toList());
    }
    public List<String> getAllIdentityNumber() {
        return clientRepository.findAll()
                .stream()
                .map(Client::getIdentityNumber)
                .collect(Collectors.toList());
    }


    public ClientResponseDto getClient(String identityNumber) throws RuntimeException {
        Optional<Client> requestedClient = clientRepository.findByIdentityNumber(identityNumber);
        if(requestedClient.isPresent()) {
            Client client = requestedClient.get();
            return clientMapper.apply(client);
        }else{
            throw new ClientNotFoundException();
        }
    }

    public ClientResponseDto updateClient(ClientDto clientDto) {

        Optional<Client> clientrequested = clientRepository.findByIdentityNumber(clientMapper.toEntity(clientDto).getIdentityNumber());
        Client existingClient = clientrequested.get();
        Client newClient = clientMapper.toEntity(clientDto);
        if (newClient.getFirstName() != null) {
            existingClient.setFirstName(newClient.getFirstName());
        }
        if (newClient.getLastName() != null) {
            existingClient.setLastName(newClient.getLastName());
        }
        if (newClient.getEmail() != null) {
            existingClient.setEmail(newClient.getEmail());
        }
        if (newClient.getPhoneNumber() != null) {
            existingClient.setPhoneNumber(newClient.getPhoneNumber());
        }
        if (newClient.getAddress() != null) {
            existingClient.setAddress(newClient.getAddress());
        }

        Client updatedClient = clientRepository.save(existingClient);

        return clientMapper.apply(updatedClient);
    }
}
