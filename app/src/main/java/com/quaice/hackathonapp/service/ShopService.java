package com.quaice.hackathonapp.service;


import android.content.Context;

import com.quaice.hackathonapp.R;
import com.quaice.hackathonapp.api.ShopApi;
import com.quaice.hackathonapp.dto.ServerResponse;
import com.quaice.hackathonapp.dto.Shop.AllShopItemResponse;
import com.quaice.hackathonapp.dto.Shop.BuyShopItemRequest;
import com.quaice.hackathonapp.dto.Shop.ShopItemRequest;
import com.quaice.hackathonapp.dto.Shop.ShopItemResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShopService {
    private ShopApi shopApi;

    public ShopService(Context context) {
        String url = context.getResources().getString(R.string.api_url);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        shopApi = retrofit.create(ShopApi.class);
    }

    public void getShopItemById(String itemId, Callback<ShopItemResponse> callback) {
        Call<ShopItemResponse> call = shopApi.getShopItemById(itemId);
        call.enqueue(callback);
    }

    public void getActiveShopItems(Callback<AllShopItemResponse> callback) {
        Call<AllShopItemResponse> call = shopApi.getActiveShopItems();
        call.enqueue(callback);
    }

    public void buyItemInShop(BuyShopItemRequest itemDto, Callback<ServerResponse> callback) {
        Call<ServerResponse> call = shopApi.buyItemInShop(itemDto);
        call.enqueue(callback);
    }
}