package com.shzlabs.themenu.data.model;

import com.google.firebase.database.PropertyName;

public class Address {

@PropertyName("address1")
private String address1;
@PropertyName("address2")
private String address2;
@PropertyName("city")
private String city;
@PropertyName("state")
private String state;
@PropertyName("country")
private String country;

public String getAddress1() {
return address1;
}

public void setAddress1(String address1) {
this.address1 = address1;
}

public String getAddress2() {
return address2;
}

public void setAddress2(String address2) {
this.address2 = address2;
}

public String getCity() {
return city;
}

public void setCity(String city) {
this.city = city;
}

public String getState() {
return state;
}

public void setState(String state) {
this.state = state;
}

public String getCountry() {
return country;
}

public void setCountry(String country) {
this.country = country;
}

}