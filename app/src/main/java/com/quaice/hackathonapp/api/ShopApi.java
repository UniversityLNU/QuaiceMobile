package com.quaice.hackathonapp.api;

import android.content.Context;

import com.quaice.hackathonapp.dto.ServerResponse;
import com.quaice.hackathonapp.dto.Shop.AllShopItemResponse;
import com.quaice.hackathonapp.dto.Shop.BuyShopItemRequest;
import com.quaice.hackathonapp.dto.Shop.ShopItemResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ShopApi {
    @GET("api/shop/GetShopItemById/{itemId}")
    Call<ShopItemResponse> getShopItemById(@Path("itemId") String itemId);

    @GET("api/shop/GetActiveShopItems")
    Call<AllShopItemResponse> getActiveShopItems();

    @POST("api/shop/BuyItemInShop")
    Call<ServerResponse> buyItemInShop(@Body BuyShopItemRequest itemDto);
}