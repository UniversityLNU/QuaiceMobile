package com.quaice.hackathonapp.dto;

public class ServerResponse {
    private String message;
    private int statusCode;

    public ServerResponse(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public int isSuccess() {
        return statusCode;
    }
}

