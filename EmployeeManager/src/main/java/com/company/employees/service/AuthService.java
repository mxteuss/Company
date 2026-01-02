package com.company.employees.service;

import com.company.employees.repository.UserRepository;
import jakarta.validation.constraints.NotBlank;
import org.jspecify.annotations.NullMarked;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @NullMarked
    public UserDetails loadUserByUsername(@NotBlank String username) throws UsernameNotFoundException {
        UserDetails user = userRepository.findByLogin(username);
        System.out.println("Username: " + user);

        return user;


    }
}