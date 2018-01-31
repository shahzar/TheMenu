package com.shzlabs.themenu.data.model;

import com.google.firebase.database.PropertyName;

import java.util.List;

public class OrderDetails {

@PropertyName("order-status")
private String orderStatus;
@PropertyName("items")
private List<FoodItem> items = null;
@PropertyName("sub-total-before-discount")
private Double subTotalBeforeDiscount;
@PropertyName("sub-total")
private Double subTotal;
@PropertyName("discount")
private Integer discount;
@PropertyName("SGST")
private Double sGST;
@PropertyName("CGST")
private Double cGST;
@PropertyName("grand-total")
private Double grandTotal;

public String getOrderStatus() {
return orderStatus;
}

public void setOrderStatus(String orderStatus) {
this.orderStatus = orderStatus;
}

public List<FoodItem> getFoodItems() {
return items;
}

public void setFoodItems(List<FoodItem> items) {
this.items = items;
}

public Double getSubTotalBeforeDiscount() {
return subTotalBeforeDiscount;
}

public void setSubTotalBeforeDiscount(Double subTotalBeforeDiscount) {
this.subTotalBeforeDiscount = subTotalBeforeDiscount;
}

public Double getSubTotal() {
return subTotal;
}

public void setSubTotal(Double subTotal) {
this.subTotal = subTotal;
}

public Integer getDiscount() {
return discount;
}

public void setDiscount(Integer discount) {
this.discount = discount;
}

public Double getSGST() {
return sGST;
}

public void setSGST(Double sGST) {
this.sGST = sGST;
}

public Double getCGST() {
return cGST;
}

public void setCGST(Double cGST) {
this.cGST = cGST;
}

public Double getGrandTotal() {
return grandTotal;
}

public void setGrandTotal(Double grandTotal) {
this.grandTotal = grandTotal;
}

}