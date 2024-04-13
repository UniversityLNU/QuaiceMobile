package com.quaice.hackathonapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.quaice.hackathonapp.adapters.FundraisingAdapter;
import com.quaice.hackathonapp.adapters.PostAdapter;
import com.quaice.hackathonapp.dto.Fundraising.AllFundraisingResponse;
import com.quaice.hackathonapp.dto.Fundraising.FundraisingResponse;
import com.quaice.hackathonapp.dto.Post.AllPostResponse;
import com.quaice.hackathonapp.service.FundraisingService;
import com.quaice.hackathonapp.service.PostService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private FundraisingService fundraisingService;
    private PostService postService;
    //fundraising
    private RelativeLayout fundLayout, postLayot;
    private RecyclerView fundraisingRecyclerView, postrecyclerView;

    //menu_selector
    private CardView fund_but, posts_but;

    private AllFundraisingResponse allFundraisingResponse;

    private void init_menu_selector(){
        fund_but = findViewById(R.id.toolbar_fund);
        posts_but = findViewById(R.id.toolbar_posts);

        fund_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFundraising();
            }
        });

        posts_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPosts();
            }
        });
    }

    private List<FundraisingResponse> search(String input){
        fundraisingService.getFundraisingsByTitleAndType(input, allFundraisingResponse);
    }
    

    public void showFundraising(){
        postLayot.setVisibility(View.GONE);
        fundLayout.setVisibility(View.VISIBLE);
        fundraisingService = new FundraisingService(this);
        postService = new PostService(this);
        //getAllfund
        fundraisingService.getAllFundraisings(new Callback<AllFundraisingResponse>() {
            @Override
            public void onResponse(Call<AllFundraisingResponse> call, Response<AllFundraisingResponse> response) {
                if (response.isSuccessful()) {
                    allFundraisingResponse = response.body();
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

        postLayot.setVisibility(View.VISIBLE);
        fundLayout.setVisibility(View.GONE);

        postService = new PostService(this);
        postService.getAllPosts(new Callback<AllPostResponse>() {
            @Override
            public void onResponse(Call<AllPostResponse> call, Response<AllPostResponse> response) {
                if (response.isSuccessful()) {
                    AllPostResponse allPostResponse = response.body();
                    if (allPostResponse != null) {
                        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                        postrecyclerView.setLayoutManager(layoutManager);
                        PostAdapter adapter = new PostAdapter(allPostResponse.getPostList(), MainActivity.this);
                        postrecyclerView.setAdapter(adapter);
                    }
                } else {
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

        init_menu_selector();

        fundLayout = findViewById(R.id.foundation_layout);
        postLayot = findViewById(R.id.main_layout);
        fundraisingRecyclerView = findViewById(R.id.fundraisingRecycler);
        postrecyclerView = findViewById(R.id.main_recycler);

        showFundraising();
    }

    // filterFundraisings
    public void filterFundraisings(AllFundraisingResponse allFundraisingResponse, String title, String fundraisingType){
        fundraisingService.getFundraisingsByTitleAndType(allFundraisingResponse, title, fundraisingType, new Callback<AllFundraisingResponse>() {
            @Override
            public void onResponse(Call<AllFundraisingResponse> call, Response<AllFundraisingResponse> response) {
                if (response.isSuccessful()) {
                    AllFundraisingResponse filteredResponse = response.body();
                    if (filteredResponse != null) {
                        LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                        fundraisingRecyclerView.setLayoutManager(layoutManager);
                        FundraisingAdapter adapter = new FundraisingAdapter(filteredResponse.getFundraisingList(), MainActivity.this);
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
}