package com.shzlabs.themenu.data.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.PropertyName;

import java.util.List;

public class FoodCategory {

    @PropertyName("category-id")
    private String categoryId;
    @PropertyName("label")
    private String label;
    @PropertyName("items-ids")
    private List<String> itemsIds = null;
    @PropertyName("display-order")
    private Integer displayOrder;
    @PropertyName("image-url")
    private String imageUrl;

    @PropertyName("category-id")
    public String getCategoryId() {
        return categoryId;
    }

    @PropertyName("category-id")
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @PropertyName("label")
    public String getLabel() {
        return label;
    }

    @PropertyName("label")
    public void setLabel(String label) {
        this.label = label;
    }

    @PropertyName("items-ids")
    public List<String> getItemsIds() {
        return itemsIds;
    }

    @PropertyName("items-ids")
    public void setItemsIds(List<String> itemsIds) {
        this.itemsIds = itemsIds;
    }

    @PropertyName("display-order")
    public Integer getDisplayOrder() {
        return displayOrder;
    }

    @PropertyName("display-order")
    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    @PropertyName("image-url")
    public String getImageUrl() {
        return imageUrl;
    }

    @PropertyName("image-url")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}