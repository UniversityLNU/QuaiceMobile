package com.quaice.hackathonapp.dto.Auth;

public class SignUpResponse {
    private String userId;
    private String jwtToken;

    public SignUpResponse(String userId, String JwtToken) {
        this.userId = userId;
        this.jwtToken = JwtToken;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
}
