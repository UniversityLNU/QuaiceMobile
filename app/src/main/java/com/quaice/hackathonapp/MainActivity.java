package com.quaice.hackathonapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.quaice.hackathonapp.adapters.FundraisingAdapter;
import com.quaice.hackathonapp.dto.Fundraising.AllFundraisingResponse;
import com.quaice.hackathonapp.dto.Post.AllPostResponse;
import com.quaice.hackathonapp.service.FundraisingService;
import com.quaice.hackathonapp.service.PostService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private FundraisingService fundraisingService;
    private PostService postService;
    //fundraising
    private RelativeLayout fundLayout;
    private RecyclerView fundraisingRecyclerView;

    public void showFundraising(){
        fundLayout = findViewById(R.id.foundation_layout);
        fundraisingRecyclerView = findViewById(R.id.fundraisingRecycler);
        fundLayout.setVisibility(View.VISIBLE);
        fundraisingService = new FundraisingService(this);
        postService = new PostService(this);
        //getAllfund
        fundraisingService.getAllFundraisings(new Callback<AllFundraisingResponse>() {
            @Override
            public void onResponse(Call<AllFundraisingResponse> call, Response<AllFundraisingResponse> response) {
                if (response.isSuccessful()) {
                    AllFundraisingResponse allFundraisingResponse = response.body();
                    if (allFundraisingResponse != null) {
                        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                        fundraisingRecyclerView.setLayoutManager(layoutManager);
                        FundraisingAdapter adapter = new FundraisingAdapter(allFundraisingResponse.getFundraisingList(), MainActivity.this);
                        fundraisingRecyclerView.setAdapter(adapter);
                    }
                } else {
                    Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AllFundraisingResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Error", t.getMessage());
            }
        });
    }
    //
    public void showPosts(){
        postService.getAllPosts(new Callback<AllPostResponse>() {
            @Override
            public void onResponse(Call<AllPostResponse> call, Response<AllPostResponse> response) {
                if (response.isSuccessful()) {
                    AllPostResponse allPostResponse = response.body();
                    if (allPostResponse != null) {
                        Toast.makeText(MainActivity.this, "" +  allPostResponse.getPostList().size(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AllPostResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Error", t.getMessage());
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