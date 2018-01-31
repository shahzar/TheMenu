package com.shzlabs.themenu.data.model;

import com.google.firebase.database.PropertyName;

public class FoodItem {

    @PropertyName("item-id")
    private String itemId;
    @PropertyName("item-name")
    private String itemName;
    @PropertyName("isVeg")
    private Boolean isVeg;
    @PropertyName("unit-price")
    private Double unitPrice;
    @PropertyName("discount-available")
    private Boolean discountAvailable;
    @PropertyName("discount-price")
    private Double discountPrice;
    @PropertyName("discount-percent")
    private Integer discountPercent;
    @PropertyName("item-category-id")
    private String itemCategoryId;


    @PropertyName("item-id")
    public String getItemId() {
        return itemId;
    }

    @PropertyName("item-id")
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @PropertyName("item-name")
    public String getItemName() {
        return itemName;
    }

    @PropertyName("item-name")
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    @PropertyName("isVeg")
    public Boolean getIsVeg() {
        return isVeg;
    }

    @PropertyName("isVeg")
    public void setIsVeg(Boolean isVeg) {
        this.isVeg = isVeg;
    }

    @PropertyName("unit-price")
    public Double getUnitPrice() {
        return unitPrice;
    }

    @PropertyName("unit-price")
    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @PropertyName("discount-available")
    public Boolean getDiscountAvailable() {
        return discountAvailable;
    }

    @PropertyName("discount-available")
    public void setDiscountAvailable(Boolean discountAvailable) {
        this.discountAvailable = discountAvailable;
    }

    @PropertyName("discount-price")
    public Double getDiscountPrice() {
        return discountPrice;
    }

    @PropertyName("discount-price")
    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    @PropertyName("discount-percent")
    public Integer getDiscountPercent() {
        return discountPercent;
    }

    @PropertyName("discount-percent")
    public void setDiscountPercent(Integer discountPercent) {
        this.discountPercent = discountPercent;
    }

    @PropertyName("item-category-id")
    public String getItemCategoryId() {
        return itemCategoryId;
    }

    @PropertyName("item-category-id")
    public void setItemCategoryId(String itemCategoryId) {
        this.itemCategoryId = itemCategoryId;
    }

}