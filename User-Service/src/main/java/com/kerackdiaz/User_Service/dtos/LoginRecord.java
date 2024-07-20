package com.kerackdiaz.User_Service.dtos;

public record LoginRecord(String email, String password) {
    public LoginRecord {
        if (email.isBlank()) {
            throw new IllegalArgumentException("Email is blank");
        }
        if (password.isBlank()) {
            throw new IllegalArgumentException("Password is blank");
        }
    }
}
