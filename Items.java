package com.example.supermarket;

public class Items {
    private String Itemname;
    private String Location;
    private String Price;

    public Items(String itemname, String location, String price) {
        Itemname = itemname;
        Location = location;
        Price = price;
    }
    public Items()
    {

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
