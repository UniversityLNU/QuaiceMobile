package com.quaice.hackathonapp.dto.Shop;

public class BuyShopItemRequest {
    private String shopItemId;
    private String userId;

    public BuyShopItemRequest(String shopItemId, String userId) {
        this.shopItemId = shopItemId;
        this.userId = userId;
    }

    public String getShopItemId() {
        return shopItemId;
    }

    public void setShopItemId(String shopItemId) {
        this.shopItemId = shopItemId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
