package com.example.pruebaSentra.service;

import com.example.pruebaSentra.dto.EmployeeResponseDTO;
import com.example.pruebaSentra.exceptions.DuplicateEmailException;
import com.example.pruebaSentra.exceptions.EmailFormatErrorException;
import com.example.pruebaSentra.model.Employee;
import com.example.pruebaSentra.utils.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.pruebaSentra.repository.UserRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public EmployeeResponseDTO saveUser(Employee employee) throws RuntimeException{
        if( !isValidEmail(employee.getEmail()) ){
            throw new EmailFormatErrorException("El formato del correo es incorrecto.");
        }
        if( !isValidPassword(employee.getPassword()) ){
            throw new EmailFormatErrorException("El formato de password es incorrecto." +
                    "Debe tener minimo 8 caracteres, " +
                    "al menos una letra minuscula, " +
                    "al menos una mayuscula, " +
                    "al menos un numero " +
                    "y uno o varios de los siguientes caracteres especiales @#$%^&+=,.:;_-");
        }

        employee.setCreated(new Date());
        employee.setModified(new Date());
        employee.setLast_login(new Date());
        employee.setIsactive("true");
        employee.setToken(jwtUtil.generateToken(employee.getEmail()));

        return modelMapper.map(userRepository.save(employee), EmployeeResponseDTO.class);
    }

    private Map<String, String> errorMessage(String message) {
        Map<String, String> error = new HashMap<>();
        error.put("mensaje", message);
        return error;
    }

    private Map<String, String> successMessage(String message) {
        Map<String, String> success = new HashMap<>();
        success.put("mensaje", message);
        return success;
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }

    private boolean isValidPassword(String password) {

        // Parámetros para la expresión regular
        int minLength = 8;
        boolean requireDigit = true;
        boolean requireLowerCase = true;
        boolean requireUpperCase = true;
        boolean requireSpecialChar = true;

        String regex = buildRegex(minLength, requireDigit, requireLowerCase, requireUpperCase, requireSpecialChar);

        return (validatorPass(password, regex));
    }

    private boolean validatorPass(String password, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private String buildRegex(int minLength, boolean requireDigit, boolean requireLowerCase, boolean requireUpperCase, boolean requireSpecialChar) {
        StringBuilder regexBuilder = new StringBuilder("^");

        regexBuilder.append("(?=.*[0-9])").append(requireDigit ? "" : "?");
        regexBuilder.append("(?=.*[a-z])").append(requireLowerCase ? "" : "?");
        regexBuilder.append("(?=.*[A-Z])").append(requireUpperCase ? "" : "?");
        regexBuilder.append("(?=.*[@#$%^&+=,.:;_-])").append(requireSpecialChar ? "" : "?");
        regexBuilder.append("(?=\\S+$)");
        regexBuilder.append(".{" + minLength + ",}$");

        return regexBuilder.toString();
    }
}

