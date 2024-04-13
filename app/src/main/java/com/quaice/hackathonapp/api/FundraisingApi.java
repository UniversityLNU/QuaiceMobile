package com.quaice.hackathonapp.api;

import com.quaice.hackathonapp.dto.Fundraising.AllFundraisingResponse;
import com.quaice.hackathonapp.dto.Fundraising.FundraisingResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface FundraisingApi {

    @GET("api/Fundraising/GetFundraisingById/{fundraisingId}")
    Call<FundraisingResponse> getFundraisingById(@Path("fundraisingId") String fundraisingId);

    @GET("api/Fundraising/GetAllFundraisings")
    Call<AllFundraisingResponse> getAllFundraisings();
}