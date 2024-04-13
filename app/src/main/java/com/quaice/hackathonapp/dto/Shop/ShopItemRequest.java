package com.quaice.hackathonapp.dto.Shop;

public class ShopItemRequest {
    private String shopItemId;
    public ShopItemRequest(String shopItemId) {
        this.shopItemId = shopItemId;
    }
    public String getShopItemId() {
        return shopItemId;
    }
    public void setShopItemId(String shopItemId) {
        this.shopItemId = shopItemId;
    }
}
