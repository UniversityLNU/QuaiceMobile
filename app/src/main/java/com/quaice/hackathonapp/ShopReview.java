package com.quaice.hackathonapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.quaice.hackathonapp.dto.ServerResponse;
import com.quaice.hackathonapp.dto.Shop.BuyShopItemRequest;
import com.quaice.hackathonapp.dto.Shop.ShopItemResponse;
import com.quaice.hackathonapp.dto.User.UserInfoResponse;
import com.quaice.hackathonapp.service.AuthService;
import com.quaice.hackathonapp.service.ShopService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopReview extends AppCompatActivity {
    private ShopService shopService;
    private AuthService authService;
    public TextView balance_count, item_name, item_description, buy_price;
    public CardView buy_button, back_button;

    public ImageView image;
    public static ShopItemResponse item;

    UserInfoResponse user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        shopService = new ShopService(this);
        authService = new AuthService(this);

        image = findViewById(R.id.image);
        Picasso.get().load(item.getItemImage()).into(image);

        String userId = AuthenticationActivity.sharedPreferences.getString("userID", "");

        authService.getUser(userId, new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                if (response.isSuccessful()) {
                    user = response.body();
                    balance_count.setText("Your Balance: " + user.getNumberOfDonatsCoins());
                }
            }

            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {
            }
        });

        back_button = findViewById(R.id.back);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        balance_count = findViewById(R.id.your_balance);
        item_name = findViewById(R.id.name);
        item_description = findViewById(R.id.description);
        buy_price = findViewById(R.id.donut_text);
        buy_button = findViewById(R.id.make_donat);



        item_name.setText(item.getTitle());
        item_description.setText(item.getDescription());
        buy_price.setText(item.getPrice().toString());

        buy_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buyItemInShop(item.getItemId());
            }
        });


    }

    public void buyItemInShop(String itemId){
        if (!user.getUserId().equals("")) {
            BuyShopItemRequest itemDto = new BuyShopItemRequest(itemId, user.getUserId());
            shopService.buyItemInShop(itemDto, new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    if(response.isSuccessful() && response.body() != null) {
                        Toast.makeText(ShopReview.this, item.getTitle() + " was successfully purchased", Toast.LENGTH_SHORT).show();
                        recreate();
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