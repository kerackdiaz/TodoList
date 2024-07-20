package com.kerackdiaz.AuthServer.models;

import com.kerackdiaz.AuthServer.dtos.UserDTO;

public class AuthResponse {
    private String accessToken;
    private UserDTO user;

    public AuthResponse(String accessToken, UserDTO user) {
        this.accessToken = accessToken;
        this.user = user;
    }

    // Getters and Setters
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}