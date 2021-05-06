package com.example.pcbuilder;

public class CartModel {
    String Name, Image, Type, Price, Power;

    public CartModel(String name, String image, String type, String price, String power) {
        Name = name;
        Image = image;
        Type = type;
        Price = price;
        Power = power;

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getPower() {
        return Power;
    }

    public void setPower(String power) {
        Power = power;
    }
}
