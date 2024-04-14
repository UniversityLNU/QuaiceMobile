package com.quaice.hackathonapp.service;

import android.content.Context;

import com.quaice.hackathonapp.R;
import com.quaice.hackathonapp.api.UserApi;
import com.quaice.hackathonapp.dto.Auth.LoginRequest;
import com.quaice.hackathonapp.dto.Auth.LoginResponse;
import com.quaice.hackathonapp.dto.Auth.SignUpRequest;
import com.quaice.hackathonapp.dto.Auth.SignUpResponse;
import com.quaice.hackathonapp.dto.User.UserInfoRequest;
import com.quaice.hackathonapp.dto.User.UserInfoResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthService {
    private UserApi userApi;

    public AuthService(Context context) {
        String url = context.getResources().getString(R.string.api_url);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url) // replace with your actual API base URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userApi = retrofit.create(UserApi.class);
    }

    public void login(String email, String password, Callback<LoginResponse> callback) {
        LoginRequest loginRequest = new LoginRequest(email, password);
        Call<LoginResponse> call = userApi.loginUser(loginRequest);
        call.enqueue(callback);
    }

    public void signUp(String fullName, String email, String password, String phoneNumber, Callback<SignUpResponse> callback) {
        SignUpRequest signUpRequest = new SignUpRequest(fullName, email, password, phoneNumber);
        Call<SignUpResponse> call = userApi.registerUser(signUpRequest);
        call.enqueue(callback);
    }

    public void getUser(String userId, Callback<UserInfoResponse> callback) {
        Call<UserInfoResponse> call = userApi.getUser(userId);
        call.enqueue(callback);
    }
}