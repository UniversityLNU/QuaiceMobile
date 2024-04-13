package com.quaice.hackathonapp.dto.Shop;

public class BuyShopItemResponse {
    private String BuyStatus;

    public BuyShopItemResponse(String BuyStatus) {
        this.BuyStatus = BuyStatus;
    }

    public String getBuyStatus() {
        return BuyStatus;
    }

    public void setBuyStatus(String buyStatus) {
        BuyStatus = buyStatus;
    }
}
