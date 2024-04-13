package com.quaice.hackathonapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.quaice.hackathonapp.service.FundraisingService;

public class MainActivity extends AppCompatActivity {
    private FundraisingService fundraisingService;
    //fundraising
    private RelativeLayout fundLayout;
    private RecyclerView fundraisingRecyclerView;

    public void showFundraising(){
        fundLayout.setVisibility(View.VISIBLE);
        fundraisingService = new FundraisingService(this);

        //getAllfund
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}