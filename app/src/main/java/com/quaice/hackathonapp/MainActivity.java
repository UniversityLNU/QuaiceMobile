package com.quaice.hackathonapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.quaice.hackathonapp.adapters.FundraisingAdapter;
import com.quaice.hackathonapp.dto.Fundraising.AllFundraisingResponse;
import com.quaice.hackathonapp.service.FundraisingService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private FundraisingService fundraisingService;
    //fundraising
    private RelativeLayout fundLayout;
    private RecyclerView fundraisingRecyclerView;

    public void showFundraising(){
        fundLayout = findViewById(R.id.foundation_layout);
        fundraisingRecyclerView = findViewById(R.id.main_recycler);
        fundLayout.setVisibility(View.VISIBLE);
        fundraisingService = new FundraisingService(this);

        //getAllfund
        fundraisingService.getAllFundraisings(new Callback<AllFundraisingResponse>() {
            @Override
            public void onResponse(Call<AllFundraisingResponse> call, Response<AllFundraisingResponse> response) {
                if (response.isSuccessful()) {
                    AllFundraisingResponse allFundraisingResponse = response.body();
                    if (allFundraisingResponse != null) {
                        // Modify this line to change how the fundraising information is displayed
                        FundraisingAdapter adapter = new FundraisingAdapter(allFundraisingResponse.getFundraisingList(), MainActivity.this);
                        fundraisingRecyclerView.setAdapter(adapter);
                    }
                } else {
                    // Handle the error
                }
            }

            @Override
            public void onFailure(Call<AllFundraisingResponse> call, Throwable t) {
                // Handle the error
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showFundraising();
    }
}