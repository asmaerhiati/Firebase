package com.example.firebase.Model;

import android.view.View;

import java.io.Serializable;

public class CategoryItem implements Serializable {

// here i am taking only image url. and this is as integer because i am using it from drawable file.

    Integer itemId;
    Integer imageUrl;
    String name;
    String price;
    String phone;
    String rating;


    public CategoryItem(Integer itemId, Integer imageUrl, String name, String price, String phone,String rating) {
        this.itemId = itemId;
        this.imageUrl = imageUrl;
        this.name=name;
        this.phone=phone;
        this.price=price;
        this.rating=rating;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPrice() {
        return price;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }
}
