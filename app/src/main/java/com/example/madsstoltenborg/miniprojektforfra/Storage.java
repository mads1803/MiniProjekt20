package com.example.madsstoltenborg.miniprojektforfra;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by simon on 13-03-2018.
 */

public class Storage {

    private static Storage storage;

    private Storage(){};
    
    public static Storage getInstance(){
        if(storage == null){
            storage = new Storage();
        }
        return storage;
    }

<<<<<<< HEAD
    // Shop CRUD Database Operations
    public void insertShop(String name, String address, String website) {
        SQLiteDatabase db = ShoppingDBHelper.getWritableDatabase();

        ContentValues shopValues = new ContentValues();
        shopValues.put("NAME", name);
        shopValues.put("ADDRESS", address);
        shopValues.put("WEBSITE", website);
        db.insert("SHOPS", null, shopValues);
    }
=======

>>>>>>> 8e2542df16f0ef9ed492c99786ef9515df5ccec1
}
