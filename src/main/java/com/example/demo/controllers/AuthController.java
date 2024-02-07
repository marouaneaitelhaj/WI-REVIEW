package com.example.demo.controllers;

import com.example.demo.entities.DbUser;
import com.example.demo.reqrsp.AuthenticationRequest;
import com.example.demo.reqrsp.AuthenticationResponse;
import com.example.demo.reqrsp.RegisterRequest;
import com.example.demo.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private  final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest authenticationRequest
    ) {
        return ResponseEntity.ok(authenticationService.login(authenticationRequest));
    }


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest registerRequest
    ) {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }



    @PostMapping("/user")
    public ResponseEntity<DbUser> currentUserName(Principal principal) {
        return ResponseEntity.ok(authenticationService.getUser(principal.getName()));
    }
}
