package com.example.pruebaSentra.exceptions;

public class EmailFormatErrorException extends RuntimeException {
    public EmailFormatErrorException(String message) {
        super(message);
    }
}