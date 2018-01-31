package com.shzlabs.themenu.data.model;

import com.google.firebase.database.PropertyName;

public class Customer {

@PropertyName("customer-id")
private String customerId;
@PropertyName("customer-name")
private String customerName;
@PropertyName("ph-no")
private Integer phNo;
@PropertyName("address")
private Address address;
@PropertyName("order-details")
private OrderDetails orderDetails;

public String getCustomerId() {
return customerId;
}

public void setCustomerId(String customerId) {
this.customerId = customerId;
}

public String getCustomerName() {
return customerName;
}

public void setCustomerName(String customerName) {
this.customerName = customerName;
}

public Integer getPhNo() {
return phNo;
}

public void setPhNo(Integer phNo) {
this.phNo = phNo;
}

public Address getAddress() {
return address;
}

public void setAddress(Address address) {
this.address = address;
}

public OrderDetails getOrderDetails() {
return orderDetails;
}

public void setOrderDetails(OrderDetails orderDetails) {
this.orderDetails = orderDetails;
}

}