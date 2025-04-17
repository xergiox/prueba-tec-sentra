package com.example.pruebaSentra.service;

import com.example.pruebaSentra.exceptions.EmailFormatErrorException;
import com.example.pruebaSentra.model.Employee;
import com.example.pruebaSentra.repository.UserRepository;
import com.example.pruebaSentra.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private UserService userService;

    private Employee employee;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        employee = new Employee();
        employee.setEmail("test@example.com");
        employee.setPassword("Test.Password1");
    }

    @Test
    public void saveUser_InvalidEmail_ThrowsEmailFormatErrorException() {
        employee.setEmail("invalid-email");

        Exception exception = assertThrows(EmailFormatErrorException.class, () -> {
            userService.saveUser(employee);
        });

        assertEquals("El formato del correo es incorrecto.", exception.getMessage());
    }

    @Test
    public void saveUser_InvalidPassword_ThrowsEmailFormatErrorException() {
        employee.setPassword("short");

        Exception exception = assertThrows(EmailFormatErrorException.class, () -> {
            userService.saveUser(employee);
        });

        assertEquals("El formato de password es incorrecto." +
                "Debe tener minimo 8 caracteres, " +
                "al menos una letra minuscula, " +
                "al menos una mayuscula, " +
                "al menos un numero " +
                "y uno o varios de los siguientes caracteres especiales @#$%^&+=,.:;_-", exception.getMessage());
    }
}
