package com.example.firebase.Model;

public class CartModel {
    String name;
    String quantity;
    String price;
    String user;
//    int totalPrice;
    String ImageUrl;
String phone;


    public CartModel() {
    }

    public CartModel(String Name, String Quantity, String Price) {
        this.name = Name;
        this.quantity = Quantity;
        this.price = Price;
    }
    public CartModel(String Name, String Quantity, String Price, String user) {
        this.name = Name;
        this.quantity = Quantity;
        this.price = Price;
        this.user = user;
    }

    public CartModel(String Name, String Quantity, String Price,String ImageUrl,String user) {
        this.name = Name;
        this.quantity = Quantity;
        this.price = Price;
        this.ImageUrl=ImageUrl;
        this.user = user;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getPrice() {
        return price;
    }
    public String getUser() {
        return user;
    }


}
