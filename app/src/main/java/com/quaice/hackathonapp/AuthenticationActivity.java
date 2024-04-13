package com.quaice.hackathonapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AuthenticationActivity extends AppCompatActivity {
    private RelativeLayout selector_layout, aunth_layout;

    private CardView select_loggin, select_reggistration, back_to_selector;

    private CardView aunth_ok;

    private CardView full_name_card, email_card, phone_number_card, password_card;

    public TextView aunth_button_text;

    private int VISIBLE = View.VISIBLE, INVISIBLE = View.INVISIBLE;

    private boolean is_loggin = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

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

            }
        });

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
        SharedPreferences sharedPreferences = getSharedPreferences("AunthPref", Context.MODE_PRIVATE);
        if(!sharedPreferences.getString("userID", "").equals("")){
            startActivity(new Intent(AuthenticationActivity.this, MainActivity.class));
        }
    }
}