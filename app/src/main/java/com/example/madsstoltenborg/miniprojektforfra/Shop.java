package com.example.madsstoltenborg.miniprojektforfra;

/**
 * Created by Mads Stoltenborg on 15-03-2018.
 */

public class Shop {
    private String name;
    private String address;
    private String website;
    private long id;

    public Shop(String name, String adress, String website) {
        this.name = name;
        this.address = adress;
        this.website = website;
    }

    public Shop(long id, String name, String adress, String website) {
        this.id = id;
        this.name = name;
        this.address = adress;
        this.website = website;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return address;
    }

    public void setAdress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
