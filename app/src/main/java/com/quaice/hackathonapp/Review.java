package com.quaice.hackathonapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quaice.hackathonapp.dto.Fundraising.FundraisingResponse;

public class Review extends AppCompatActivity {
    public static FundraisingResponse current;
    private RelativeLayout make_donate;
    private CardView back;
    private TextView title, description, type, company;

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        back = findViewById(R.id.back);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        type = findViewById(R.id.type);
        company = findViewById(R.id.company);
        //make_donate = findViewById(R.id.make_donate);

        title.setText(current.getTitle());
        description.setText(current.getDescription());
        type.setText(current.getFundraisingType().toString());
        company.setText(current.getFundraisingCompany());

        webView = findViewById(R.id.webView);

        // Enable JavaScript (optional)
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.loadUrl(current.getFundraisingUrl());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        make_donate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String url = current.getFundraisingUrl();
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse(url));
//                startActivity(intent);
//            }
//        });
    }
}