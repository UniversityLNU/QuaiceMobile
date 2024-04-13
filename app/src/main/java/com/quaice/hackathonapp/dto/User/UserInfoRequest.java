package com.quaice.hackathonapp.dto.User;

public class UserInfoRequest {
    private String userId;

    public UserInfoRequest(String userId) {
        this.userId = userId;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
}
