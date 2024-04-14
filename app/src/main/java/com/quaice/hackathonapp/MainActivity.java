package com.quaice.hackathonapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.quaice.hackathonapp.adapters.FundraisingAdapter;
import com.quaice.hackathonapp.adapters.PostAdapter;
import com.quaice.hackathonapp.dto.Fundraising.AllFundraisingResponse;
import com.quaice.hackathonapp.dto.Fundraising.FundraisingResponse;
import com.quaice.hackathonapp.dto.Post.AllPostResponse;
import com.quaice.hackathonapp.dto.Post.CreatePostResponse;
import com.quaice.hackathonapp.dto.User.UserInfoResponse;
import com.quaice.hackathonapp.service.AuthService;
import com.quaice.hackathonapp.service.FundraisingService;
import com.quaice.hackathonapp.service.PostService;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private FundraisingService fundraisingService;
    private PostService postService;
    private AuthService authService;
    //fundraising
    private RelativeLayout fundLayout, postLayot, profileLayout;
    private RecyclerView fundraisingRecyclerView, postrecyclerView;

    //menu_selector
    private CardView fund_but, posts_but, profile_but, camera_but;

    private TextInputEditText search;

    private AllFundraisingResponse allFundraisingResponse;

    private void init_your_profile(UserInfoResponse user){
        postLayot.setVisibility(View.GONE);
        profileLayout.setVisibility(View.VISIBLE);
        fundLayout.setVisibility(View.GONE);
        TextView nickname = findViewById(R.id.your_nickname);
        TextView count = findViewById(R.id.count_of_your_coins);
        nickname.setText(user.getFullName());
        count.setText(user.getNumberOfDonatsCoins().toString());
    }

    private void init_menu_selector(){
        fund_but = findViewById(R.id.toolbar_fund);
        posts_but = findViewById(R.id.toolbar_posts);
        profile_but = findViewById(R.id.toolbar_profile);

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

        profile_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("AunthPref", Context.MODE_PRIVATE);
                Toast.makeText(MainActivity.this, sharedPreferences.getString("userID", ""), Toast.LENGTH_SHORT).show();
                //showYourProfile(sharedPreferences.getString("userID", ""));
            }
        });


    }


    

    public void showFundraising(){
        postLayot.setVisibility(View.GONE);
        profileLayout.setVisibility(View.GONE);
        fundLayout.setVisibility(View.VISIBLE);
        fundraisingService = new FundraisingService(this);
        postService = new PostService(this);
        authService = new AuthService(this);
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
        profileLayout.setVisibility(View.GONE);

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

        camera_but = findViewById(R.id.camera_button);
        camera_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreatePostActivity.class));
            }
        });

        fundLayout = findViewById(R.id.foundation_layout);
        postLayot = findViewById(R.id.main_layout);
        fundraisingRecyclerView = findViewById(R.id.fundraisingRecycler);
        postrecyclerView = findViewById(R.id.main_recycler);
        profileLayout = findViewById(R.id.your_profile_layout);

        showFundraising();

        search = findViewById(R.id.search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Do something before the text changes (optional)
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String newText = s.toString();
                filterFundraisings(allFundraisingResponse, newText, "All");
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Do something after the text changes (optional)
            }
        });
        TextView textAll = findViewById(R.id.text_all);
        TextView textMedical = findViewById(R.id.text_medical);
        TextView textClothes = findViewById(R.id.text_clothes);
        TextView textEquipment = findViewById(R.id.text_equipment);
        TextView textArmy = findViewById(R.id.text_army);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = (TextView) v;
                String filter = textView.getText().toString();
                filterFundraisings(allFundraisingResponse, "", filter);
            }
        };

        textAll.setOnClickListener(listener);
        textMedical.setOnClickListener(listener);
        textClothes.setOnClickListener(listener);
        textEquipment.setOnClickListener(listener);
        textArmy.setOnClickListener(listener);
        //scheduleAlarms();
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
    @SuppressLint("ScheduleExactAlarm")
    public void scheduleAlarms() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        // Set the alarm to start immediately and repeat every 10 seconds
        long triggerAtMillis = System.currentTimeMillis();
        long intervalMillis = 5 * 100; // 10 seconds

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerAtMillis, alarmIntent);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerAtMillis, alarmIntent);
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, triggerAtMillis, alarmIntent);
        }

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, triggerAtMillis, intervalMillis, alarmIntent);



        // Set the alarm to start at specific times (e.g., 8:00 a.m., 1:00 p.m., and 6:00 p.m.)
//        Calendar calendar1 = Calendar.getInstance();
//        calendar1.setTimeInMillis(System.currentTimeMillis()+1000);
//        calendar1.set(Calendar.HOUR_OF_DAY, 5);
//
//        Calendar calendar2 = Calendar.getInstance();
//        calendar2.setTimeInMillis(System.currentTimeMillis());
//        calendar2.set(Calendar.HOUR_OF_DAY, 13);
//
//        Calendar calendar3 = Calendar.getInstance();
//        calendar3.setTimeInMillis(System.currentTimeMillis());
//        calendar3.set(Calendar.HOUR_OF_DAY, 18);
//
//        // Set the alarms
//        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar1.getTimeInMillis(),
//                AlarmManager.INTERVAL_DAY, alarmIntent);
//        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar2.getTimeInMillis(),
//                AlarmManager.INTERVAL_DAY, alarmIntent);
//        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar3.getTimeInMillis(),
//                AlarmManager.INTERVAL_DAY, alarmIntent);
    }
    // getUserInfo
    public void showYourProfile(String userId){
        authService.getUser(userId, new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                if (response.isSuccessful()) {
                    UserInfoResponse userInfoResponse = response.body();
                    if (userInfoResponse != null) {
                        init_your_profile(userInfoResponse);
                    }
                } else {
                    Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Error", t.getMessage());
            }
        });
    }

    // use uploadUserPost

}