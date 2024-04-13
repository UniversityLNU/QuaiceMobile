package com.quaice.hackathonapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.quaice.hackathonapp.dto.Fundraising.FundraisingResponse;

public class Review extends AppCompatActivity {
    public static FundraisingResponse current;

    private CardView back, make_donat;
    private TextView title, description, type, company;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        back = findViewById(R.id.back);
        make_donat = findViewById(R.id.make_donat);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        type = findViewById(R.id.type);
        company = findViewById(R.id.company);

        title.setText(current.getTitle());
        description.setText(current.getDescription());
        type.setText(current.getFundraisingType().toString());
        company.setText(current.getFundraisingCompany());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        make_donat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}