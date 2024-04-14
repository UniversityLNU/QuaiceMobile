package com.quaice.hackathonapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.quaice.hackathonapp.adapters.ShopAdapter;
import com.quaice.hackathonapp.dto.Shop.AllShopItemResponse;
import com.quaice.hackathonapp.service.ShopService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopListActivity extends AppCompatActivity {
    private ShopService shopService;
    private RecyclerView shopRecyclerView;

    private CardView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);
        shopRecyclerView = findViewById(R.id.shop_recycler);
        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        shopService = new ShopService(this);
        getAllShopItems();
    }

    public void getAllShopItems() {
        shopService.getActiveShopItems(new Callback<AllShopItemResponse>() {
            @Override
            public void onResponse(Call<AllShopItemResponse> call, Response<AllShopItemResponse> response) {
                if(response.isSuccessful() && response.body() != null) {
                    LinearLayoutManager layoutManager = new LinearLayoutManager(ShopListActivity.this);
                    shopRecyclerView.setLayoutManager(layoutManager);
                     ShopAdapter adapter = new ShopAdapter(response.body().getShopList(), ShopListActivity.this);
                     shopRecyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(ShopListActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AllShopItemResponse> call, Throwable t) {
                Toast.makeText(ShopListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}