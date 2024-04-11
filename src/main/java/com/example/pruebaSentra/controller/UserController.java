package com.example.pruebaSentra.controller;

import com.example.pruebaSentra.dto.EmployeeResponseDTO;
import com.example.pruebaSentra.exceptions.ErrorMessage;
import com.example.pruebaSentra.model.Employee;
import com.example.pruebaSentra.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.pruebaSentra.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;
    private HttpStatus status;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Employee employee) {
        try{
            return ResponseEntity.ok(userService.saveUser(employee));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(new ErrorMessage(e.getMessage()));
        }
    }
}
