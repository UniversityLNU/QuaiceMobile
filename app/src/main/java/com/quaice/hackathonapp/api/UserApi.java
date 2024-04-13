package com.quaice.hackathonapp.api;
import com.quaice.hackathonapp.dto.Auth.LoginRequest;
import com.quaice.hackathonapp.dto.Auth.LoginResponse;
import com.quaice.hackathonapp.dto.Auth.SignUpRequest;
import com.quaice.hackathonapp.dto.Auth.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {
    @POST("api/User/login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @POST("api/User/register")
    Call<SignUpResponse> registerUser(@Body SignUpRequest signUpRequest);
}
