package com.example.supermaket_user;

public class Items {
    private String Itemname;
    private String Location;
    private String Price;
    private String Image;

    public Items(String itemname, String location, String price,String image) {
        Itemname = itemname;
        Location = location;
        Price = price;
        Image=image;
    }
    public Items()
    {

    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getItemname() {
        return Itemname;
    }

    public void setItemname(String itemname) {
        Itemname = itemname;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
