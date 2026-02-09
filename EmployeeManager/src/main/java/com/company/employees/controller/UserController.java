package com.company.employees.controller;

import com.company.employees.models.auth.AuthenticationDTO;
import com.company.employees.models.auth.RegisterDTO;
import com.company.employees.models.auth.User;
import com.company.employees.repository.UserRepository;
import com.company.employees.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Tag(name = "authentication", description = "Endpoints para autenticação e registro de usuários")
public class UserController {
    public UserRepository userRepository;
    public AuthService authService;


    @Operation(summary = "User Login", description = "Autentica o usuário e retorna um token JWT.")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
      return ResponseEntity.ok(authService.login(data));
    }

    @Operation(summary = "User Registration", description = "Registra um novo usuário no sistema.")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(userRepository.findByLogin(data.login()) != null){
            return ResponseEntity.badRequest().build();
        }
        String encrypted = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.login(), encrypted, data.role());

        userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }
}