package com.example.madsstoltenborg.miniprojektforfra;

/**
 * Created by Hansen on 15-03-2018.
 */

public class GroceryList {
    private long id;
    private String name;

    public GroceryList(long id, String name){
        this.id = id;
        this.name = name;
    }

    public String getName(){ return this.name; }
    public void setName(String name){ this.name = name; }
    public long getId(){ return this.id; }
}
