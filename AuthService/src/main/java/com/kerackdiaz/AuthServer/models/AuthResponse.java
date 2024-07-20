package com.kerackdiaz.AuthServer.models;

public class AuthResponse {

    private String accessToken;
    private String refreshToken;

    public AuthResponse(String accessToken) {
        this.accessToken = accessToken;

    }
}
