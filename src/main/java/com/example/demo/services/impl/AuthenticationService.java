package com.example.demo.services.impl;

import com.example.demo.config.JwtService;
import com.example.demo.reqrsp.AuthenticationRequest;
import com.example.demo.reqrsp.AuthenticationResponse;
import com.example.demo.reqrsp.RegisterRequest;
import com.example.demo.entities.DbUser;
import com.example.demo.enums.Role;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.intr.AuthenticationServiceInterface;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Data
@Service
public class AuthenticationService  implements AuthenticationServiceInterface {

    private  final UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private final JwtService jwtService;


    private  final AuthenticationManager authenticationManager;
    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        var user = userRepository.findByUsername(authenticationRequest.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var user = DbUser.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
    @Override
    public DbUser getUser(String name) {
        return userRepository.findByUsername(name).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
