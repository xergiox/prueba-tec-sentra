package com.example.pruebaSentra.dto;

import lombok.Data;

import java.util.Date;

@Data
public class EmployeeResponseDTO {
    private String id;
    private Date created;
    private Date modified;
    private Date last_login;
    private String token;
    private boolean isactive;
}
