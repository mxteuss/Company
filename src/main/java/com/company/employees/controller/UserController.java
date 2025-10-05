package com.company.employees.controller;

import com.company.employees.domain.model.DTO.AuthenticationDTO;
import com.company.employees.domain.model.DTO.LoginDTO;
import com.company.employees.domain.model.DTO.RegisterDTO;
import com.company.employees.domain.model.User;
import com.company.employees.infra.security.TokenService;
import com.company.employees.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class UserController {
    public AuthenticationManager authenticationManager;
    private TokenService tokenService;
    public UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginDTO(token));
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
