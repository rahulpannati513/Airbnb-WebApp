package com.rahul.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthResponse {
    private String jwt;
    public AuthResponse() {
    }

    public AuthResponse(String jwt) {
        this.jwt = jwt;
    }
}
