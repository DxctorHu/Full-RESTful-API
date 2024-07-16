package com.example.demo.response;

public class LoginResponse {
    private String token;
    private long expiresIn;

    // Getter for token
    public String getToken() {
        return token;
    }

    // Setter for token
    public void setToken(String token) {
        this.token = token;
    }

    // Getter for expiresIn
    public long getExpiresIn() {
        return expiresIn;
    }

    // Setter for expiresIn
    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
