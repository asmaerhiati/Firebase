package com.example.firebase.Model;

public class Food {
    private String Name,ImageUrl,Description,Price,MenuId,Phone;
    public Food() {

    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public Food(String name, String image, String description, String price, String menuId,String phone) {
        Name = name;
        this.ImageUrl = image;
        this.Description = description;
        this.Price = price;
        this.MenuId = menuId;
        this.Phone=phone;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setImage(String image) {
        ImageUrl = image;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }

    public String getName() {
        return Name;
    }

    public String getImage() {
        return ImageUrl;
    }

    public String getDescription() {
        return Description;
    }

    public String getPrice() {
        return Price;
    }

    public String getMenuId() {
        return MenuId;
    }
}
