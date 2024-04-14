package com.quaice.hackathonapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.quaice.hackathonapp.dto.ServerResponse;
import com.quaice.hackathonapp.dto.Shop.AllShopItemResponse;
import com.quaice.hackathonapp.dto.Shop.BuyShopItemRequest;
import com.quaice.hackathonapp.service.AuthService;
import com.quaice.hackathonapp.service.ShopService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopReview extends AppCompatActivity {
    private ShopService shopService;
    private AuthService authService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        shopService = new ShopService(this);

    }

    public void buyItemInShop(String itemId){
        SharedPreferences sharedPreferences = getSharedPreferences("AunthPref", Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("userID", "");

        if (!userId.equals("")) {
            BuyShopItemRequest itemDto = new BuyShopItemRequest(itemId, userId);
            shopService.buyItemInShop(itemDto, new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    if(response.isSuccessful() && response.body() != null) {
                        // Handle successful purchase here
                    } else {
                        Toast.makeText(ShopReview.this, response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ServerResponse> call, Throwable t) {
                    Toast.makeText(ShopReview.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(ShopReview.this, "User is not loggined", Toast.LENGTH_SHORT).show();
        }
    }
}