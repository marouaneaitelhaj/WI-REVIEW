package com.example.demo.reqrsp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class RegisterRequest {
    private  String username;

    private String password;
}
