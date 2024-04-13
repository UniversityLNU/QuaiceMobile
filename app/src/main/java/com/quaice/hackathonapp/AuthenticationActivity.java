package com.quaice.hackathonapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.quaice.hackathonapp.dto.Auth.LoginResponse;
import com.quaice.hackathonapp.dto.Auth.SignUpResponse;
import com.quaice.hackathonapp.service.AuthService;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthenticationActivity extends AppCompatActivity {
    private RelativeLayout selector_layout, aunth_layout;

    private CardView select_loggin, select_reggistration, back_to_selector;

    private CardView aunth_ok;

    private CardView full_name_card, email_card, phone_number_card, password_card;

    private TextView aunth_button_text;

    private TextInputEditText full_name, email, password, phone_number;

    private AuthService authService;

    private int VISIBLE = View.VISIBLE, INVISIBLE = View.INVISIBLE;

    private boolean is_loggin = false;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        authService = new AuthService(this);

        checkIfLoggined();

        bind_views();
        change_visibility_of_layout(VISIBLE, INVISIBLE);

        select_loggin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                is_loggin = true;
                change_visibility_of_layout(INVISIBLE, VISIBLE);
            }
        });

        select_reggistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                is_loggin = false;
                change_visibility_of_layout(INVISIBLE, VISIBLE);
            }
        });

        back_to_selector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_visibility_of_layout(VISIBLE, INVISIBLE);
            }
        });

        aunth_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    aunth();
            }
        });

    }

    private void aunth() {
        if(is_loggin){
            authService.login(email.getText().toString(), password.getText().toString(), new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if(response.isSuccessful() && response.body() != null) {
                        editor.putString("userID", response.body().getUserId());
                        editor.commit();
                        Log.println(Log.INFO, "login", response.body().getUserId());
                        checkIfLoggined();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Log.println(Log.ERROR, "Aunth", t.getMessage());
                }
            });
        }else{
            authService.signUp(full_name.getText().toString(),
                    email.getText().toString(),
                    password.getText().toString(),
                    phone_number.getText().toString(), new Callback<SignUpResponse>() {
                        @Override
                        public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                            if(response.isSuccessful() && response.body() != null) {
                                editor.putString("userID", response.body().getUserId());
                                editor.commit();
                                Log.println(Log.INFO, "signUp", response.body().getUserId());
                                checkIfLoggined();
                            }
                        }

                        @Override
                        public void onFailure(Call<SignUpResponse> call, Throwable t) {
                            Log.println(Log.ERROR, "Aunth", t.getMessage());
                        }
                    });
        }
    }

    private void bind_views(){
        selector_layout = findViewById(R.id.selector_layout);
        aunth_layout = findViewById(R.id.aunth_layout);

        select_loggin = findViewById(R.id.select_login);
        select_reggistration = findViewById(R.id.select_register);
        back_to_selector = findViewById(R.id.back);

        full_name_card = findViewById(R.id.full_name_card);
        email_card = findViewById(R.id.email_card);
        phone_number_card = findViewById(R.id.phone_number_card);
        password_card = findViewById(R.id.password_card);

        aunth_button_text = findViewById(R.id.aunth_button);
        aunth_ok = findViewById(R.id.push_aunth);

        full_name = findViewById(R.id.full_name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        phone_number = findViewById(R.id.phone_number);
    }

    private void change_visibility_of_layout(int first_lay, int second_lay){
        selector_layout.setVisibility(first_lay);
        aunth_layout.setVisibility(second_lay);
        if(second_lay == VISIBLE)
            activateAunth();
    }

    private void activateAunth(){
        if(is_loggin){
            change_some_state(full_name_card, View.GONE);
            change_some_state(email_card, VISIBLE);
            change_some_state(phone_number_card , View.GONE);
            change_some_state(password_card, VISIBLE);
            aunth_button_text.setText(R.string.login);
        }else{
            change_some_state(full_name_card, VISIBLE);
            change_some_state(email_card, VISIBLE);
            change_some_state(phone_number_card , VISIBLE);
            change_some_state(password_card, VISIBLE);
            aunth_button_text.setText(R.string.register);
        }
    }

    private void change_some_state(View view, int state){
        view.setVisibility(state);
    }

    private void checkIfLoggined(){
        sharedPreferences = getSharedPreferences("AunthPref", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        if(!sharedPreferences.getString("userID", "").equals("")){
            startActivity(new Intent(AuthenticationActivity.this, MainActivity.class));
        }
    }
}