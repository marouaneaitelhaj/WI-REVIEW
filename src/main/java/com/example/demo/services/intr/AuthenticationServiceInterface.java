package com.example.demo.services.intr;

import com.example.demo.entities.DbUser;
import com.example.demo.reqrsp.AuthenticationRequest;
import com.example.demo.reqrsp.AuthenticationResponse;
import com.example.demo.reqrsp.RegisterRequest;

public interface AuthenticationServiceInterface {
    AuthenticationResponse login(AuthenticationRequest authenticationRequest);

    AuthenticationResponse register(RegisterRequest registerRequest);

    DbUser getUser(String name);
}
