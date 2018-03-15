package com.example.madsstoltenborg.miniprojektforfra;

/**
 * Created by Mads Stoltenborg on 15-03-2018.
 */

public class Product {
    private String name;
    private long id;
    private double price;
    private long shopId;

    public Product(String name, double price, long shopId) {
        this(-1, name, price, shopId);

    }


    public Product(long id, String name, double price, long shopId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.shopId = shopId;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }
}
