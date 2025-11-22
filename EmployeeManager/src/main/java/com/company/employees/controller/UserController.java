package com.company.employees.controller;

import com.company.employees.models.auth.AuthenticationDTO;
import com.company.employees.models.auth.LoginDTO;
import com.company.employees.models.auth.RegisterDTO;
import com.company.employees.models.auth.User;
import com.company.employees.infra.TokenService;
import com.company.employees.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class UserController {
    public AuthenticationManager authenticationManager;
    private TokenService tokenService;
    public UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
            var auth = this.authenticationManager.authenticate(usernamePassword);
            var token = tokenService.generateAccessToken((User) auth.getPrincipal());
            return ResponseEntity.ok(new LoginDTO(token));
        }
        catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
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