package com.example.madsstoltenborg.miniprojektforfra;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by simon on 13-03-2018.
 */

public class Storage {

    private static Storage storage;
    private static ShoppingDBHelper shoppingDatabaseHelper;

    public static Storage getInstance(){
        if(storage == null){
            storage = new Storage();
        }
        return storage;
    }
<<<<<<< HEAD
    public static void addDummyData() {
        // Tilføjer dummy butikker, såfremt der ikke er nogle data i butikstabellen
        SQLiteDatabase db = shoppingDatabaseHelper.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM SHOPS", null);
        if (c.getCount() == 0) {

            insertShop("Rema 1000", "Møllevangs 10, 8210", "www.rema.dk");
            insertShop("Kvickly", "aabyhojvej 19, 8210", "www.kvickly.dk");
            insertShop("Bilka", "bilkavej 10, 8000","www.bilka.dk" );
=======
    
    // Shop CRUD Database Operations
    public void insertShop(String name, String address, String website) {
        SQLiteDatabase db = ShoppingDBHelper.getWritableDatabase();
>>>>>>> a67e8b61fdf0f7590a0a8c3cb3d973720df04550

        }
    }
    // Shop CRUD Database Operations
    public static void insertShop(String name, String address, String website) {
        SQLiteDatabase db = shoppingDatabaseHelper.getWritableDatabase();
        ContentValues shopValues = new ContentValues();
        shopValues.put("NAME", name);
        shopValues.put("ADDRESS", address);
        shopValues.put("WEBSITE", website);
        db.insert("SHOP", null, shopValues);
    }
    // én
    public Cursor getShop(long id)
    {
        SQLiteDatabase db = shoppingDatabaseHelper.getReadableDatabase();
        return db.query("SHOP", new String[]{"_id", "NAME", "ADDRESS", "WEBSITE"},
                null, null, null, null, null, null);
    }

}
