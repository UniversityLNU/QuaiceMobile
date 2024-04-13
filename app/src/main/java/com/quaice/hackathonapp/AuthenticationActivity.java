package com.quaice.hackathonapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class AuthenticationActivity extends AppCompatActivity {
    private RelativeLayout selector_layout, aunth_layout;

    private CardView select_loggin, select_reggistration, back_to_selector;

    private boolean is_loggin = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        bind_views();
        change_visibility_of_layout(true, false);

        select_loggin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                is_loggin = true;
                change_visibility_of_layout(false, true);
            }
        });

        select_reggistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                is_loggin = false;
                change_visibility_of_layout(false, true);
            }
        });

        back_to_selector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                change_visibility_of_layout(true, false);
            }
        });

    }

    private void bind_views(){
        selector_layout = findViewById(R.id.selector_layout);
        aunth_layout = findViewById(R.id.aunth_layout);

        select_loggin = findViewById(R.id.select_login);
        select_reggistration = findViewById(R.id.select_register);
        back_to_selector = findViewById(R.id.back);
    }

    private void change_visibility_of_layout(boolean first_lay, boolean second_lay){
        selector_layout.setActivated(first_lay);
        aunth_layout.setActivated(second_lay);
    }
}