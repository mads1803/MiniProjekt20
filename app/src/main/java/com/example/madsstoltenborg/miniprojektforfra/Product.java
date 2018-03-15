package com.example.madsstoltenborg.miniprojektforfra;

/**
 * Created by Mads Stoltenborg on 15-03-2018.
 */

public class Product {
    private String name;
    private String volume;
    private long id;


    public Product(String name, double price, long shopId) {
        this(-1, name, price, shopId);

    }

    public Product(long id, String name, double price, long shopId) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
