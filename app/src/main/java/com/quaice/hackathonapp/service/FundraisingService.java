package com.quaice.hackathonapp.service;


import android.content.Context;

import com.quaice.hackathonapp.R;
import com.quaice.hackathonapp.api.FundraisingApi;
import com.quaice.hackathonapp.dto.Fundraising.AllFundraisingResponse;
import com.quaice.hackathonapp.dto.Fundraising.FundraisingResponse;

import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FundraisingService {
    private FundraisingApi fundraisingApi;

    public FundraisingService(Context context) {
        String url = context.getResources().getString(R.string.api_url);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        fundraisingApi = retrofit.create(FundraisingApi.class);
    }

    public void getFundraisingById(String fundraisingId, Callback<FundraisingResponse> callback) {
        Call<FundraisingResponse> call = fundraisingApi.getFundraisingById(fundraisingId);
        call.enqueue(callback);
    }

    public void getAllFundraisings(Callback<AllFundraisingResponse> callback) {
        Call<AllFundraisingResponse> call = fundraisingApi.getAllFundraisings();
        call.enqueue(callback);
    }

    // filter by title and FundraisingType, user already existed getAllFundraisings call and then filter
    public void getFundraisingsByTitleAndType(AllFundraisingResponse allFundraisingResponse, String title, String fundraisingType, Callback<AllFundraisingResponse> callback) {
        AllFundraisingResponse filteredResponse = new AllFundraisingResponse(new LinkedList<>());
        for (FundraisingResponse fundraisingResponse : allFundraisingResponse.getFundraisingList()) {
            if (fundraisingResponse.getTitle().contains(title) && (fundraisingType.equals("All") || fundraisingResponse.getFundraisingType().equals(fundraisingType)) ) {
                filteredResponse.getFundraisingList().add(fundraisingResponse);
            }
        }
        callback.onResponse(null, retrofit2.Response.success(filteredResponse));
    }

}