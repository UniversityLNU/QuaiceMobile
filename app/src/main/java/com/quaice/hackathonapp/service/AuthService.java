package com.quaice.hackathonapp.service;

import com.quaice.hackathonapp.api.UserApi;
import com.quaice.hackathonapp.dto.Auth.LoginRequest;
import com.quaice.hackathonapp.dto.Auth.LoginResponse;
import com.quaice.hackathonapp.dto.Auth.SignUpRequest;
import com.quaice.hackathonapp.dto.Auth.SignUpResponse;

import java.io.IOException;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthService {
    private UserApi userApi;

    public AuthService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://your-api-url.com/") // replace with your actual API base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userApi = retrofit.create(UserApi.class);
    }

    public LoginResponse login(String email, String password) throws IOException {
        LoginRequest loginRequest = new LoginRequest(email, password);
        Response<LoginResponse> response = userApi.loginUser(loginRequest).execute();
        if (response.isSuccessful()) {
            return response.body();
        } else {
            throw new IOException(response.errorBody() != null ? response.errorBody().string() : "Unknown error");
        }
    }

    public SignUpResponse signUp(String fullName, String email, String password, String phoneNumber) throws IOException {
        SignUpRequest signUpRequest = new SignUpRequest(fullName, email, password, phoneNumber);
        Response<SignUpResponse> response = userApi.registerUser(signUpRequest).execute();
        if (response.isSuccessful()) {
            return response.body();
        } else {
            throw new IOException(response.errorBody() != null ? response.errorBody().string() : "Unknown error");
        }
    }
}