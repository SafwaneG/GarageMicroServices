package com.garagesystem.clientservice.repository;

import com.garagesystem.clientservice.model.entity.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {
    Optional<Client> findByIdentityNumber(String identityNumber);
}
