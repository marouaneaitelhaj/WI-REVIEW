package com.example.demo.reqrsp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class AuthenticationResponse {
    private final String token;
}
