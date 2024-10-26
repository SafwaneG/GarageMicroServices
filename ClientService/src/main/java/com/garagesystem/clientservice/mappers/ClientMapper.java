package com.garagesystem.clientservice.mappers;

import com.garagesystem.clientservice.model.dto.ClientDto;
import com.garagesystem.clientservice.model.entity.Client;
import com.mongodb.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class ClientMapper implements Function<Client, ClientDto> {
    @Override
    public ClientDto apply(Client client) {
        return new ClientDto(client.getIdentityNumber(),
                client.getFirstName(),
                client.getLastName(),
                client.getPhoneNumber(),
                client.getEmail(),
                client.getAddress());
    }

    public Client toEntity(ClientDto clientDto) {
        return new Client(clientDto.identityNumber(),
                clientDto.firstName(),
                clientDto.lastName(),
                clientDto.address(),
                clientDto.email(),
                clientDto.phoneNumber()
                );
    }
}
