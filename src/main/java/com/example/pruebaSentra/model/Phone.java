package com.example.pruebaSentra.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String citycode;
    private String contrycode;
}
