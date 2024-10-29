package com.garagesystem.clientservice.controllerAdvice;

import com.garagesystem.clientservice.exception.ClientNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ClientControllerAdvice {
    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<String> handler() {
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body("There is no client with this identity number!");
    }
}
