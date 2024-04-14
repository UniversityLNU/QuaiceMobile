package com.quaice.hackathonapp.dto.Shop;

public class BuyShopItemRequest {
    private String itemId;
    private String userId;

    public BuyShopItemRequest(String shopItemId, String userId) {
        this.itemId = shopItemId;
        this.userId = userId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
