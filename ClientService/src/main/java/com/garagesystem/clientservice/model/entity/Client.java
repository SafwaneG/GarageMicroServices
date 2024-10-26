package com.garagesystem.clientservice.model.entity;

import com.garagesystem.clientservice.model.entity.Address;
import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "clientCollection")
public class Client {
    @Id
    private String id;
    @NonNull
    @Indexed(unique = true)
    private String identityNumber;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private Address address;
    @NonNull
    private String phoneNumber;
    private String email;

    public Client(@NonNull String identityNumber, @NonNull String firstName, @NonNull String lastName, @NonNull Address address, @NonNull String phoneNumber, String email) {
        this.identityNumber = identityNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
