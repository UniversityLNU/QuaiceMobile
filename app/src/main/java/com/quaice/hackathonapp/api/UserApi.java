package com.quaice.hackathonapp.api;
import com.quaice.hackathonapp.dto.Auth.LoginRequest;
import com.quaice.hackathonapp.dto.Auth.LoginResponse;
import com.quaice.hackathonapp.dto.Auth.SignUpRequest;
import com.quaice.hackathonapp.dto.Auth.SignUpResponse;
import com.quaice.hackathonapp.dto.User.UserInfoRequest;
import com.quaice.hackathonapp.dto.User.UserInfoResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserApi {
    @POST("/api/User/login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @POST("/api/User/register")
    Call<SignUpResponse> registerUser(@Body SignUpRequest signUpRequest);
    //  api/User/info
    @GET("/api/User/GetUser/{userId}")
    Call<UserInfoResponse> getUser(@Path("userId") String userId);


}
