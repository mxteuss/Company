package com.company.employees.controller;

import com.company.employees.models.auth.AuthenticationDTO;
import com.company.employees.models.auth.RegisterDTO;
import com.company.employees.models.auth.User;
import com.company.employees.repository.UserRepository;
import com.company.employees.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class UserController {
    public UserRepository userRepository;
    public AuthService authService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
      return ResponseEntity.ok(authService.login(data));
    }

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