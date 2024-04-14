package com.quaice.hackathonapp.dto.Shop;

public class ShopItemResponse {
    private String shopItemId;
    private String title;
    private Double price;
    private String description;
    private String itemImage;

   public ShopItemResponse(String shopItemId, String title, Double price, String description, String itemImage) {
            this.shopItemId = shopItemId;
            this.title = title;
            this.price = price;
            this.description = description;
            this.itemImage = itemImage;
        }

    public String getShopItemId() {
        return shopItemId;
    }

    public void setShopItemId(String shopItemId) {
        this.shopItemId = shopItemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }
}
