package com.quaice.hackathonapp.dto.Shop;

import java.util.List;

public class AllShopItemResponse {
    private List<ShopItemResponse> shopList;

    public AllShopItemResponse(List<ShopItemResponse> shopList) {
        this.shopList = shopList;
    }

    public List<ShopItemResponse> getShopList() {
        return shopList;
    }

    public void setShopList(List<ShopItemResponse> shopList) {
        this.shopList = shopList;
    }
}
