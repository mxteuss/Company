package com.company.employees.service;

import com.company.employees.infra.TokenService;
import com.company.employees.models.auth.AuthenticationDTO;
import com.company.employees.models.auth.LoginDTO;
import com.company.employees.models.auth.User;
import com.company.employees.repository.UserRepository;
import jakarta.validation.constraints.NotBlank;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;


    public AuthService(UserRepository userRepository, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @Override
    @NullMarked
    public UserDetails loadUserByUsername(@NotBlank String username) throws UsernameNotFoundException {
        UserDetails user = userRepository.findByLogin(username);

        System.out.println("Username: " + user);
        return user;
    }

    public LoginDTO login(AuthenticationDTO authenticationDTO) {
        var user = new UsernamePasswordAuthenticationToken(authenticationDTO.login(), authenticationDTO.password());
        var auth = authenticationManager.authenticate(user);
        var token = tokenService.generateAccessToken((User) Objects.requireNonNull(auth.getPrincipal()));

        return new LoginDTO(token);
    }
}