package com.quaice.hackathonapp.dto.Auth;

public class LoginResponse {
    private String userId;
    private String jwtToken;
    public LoginResponse(String userId, String JwtToken) {
        this.userId = userId;
        this.jwtToken = JwtToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
