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
    private static ShoppingDBHelper shoppingDatabaseHelper = ShoppingDBHelper.getInstance();

    public static Storage getInstance(){
        if(storage == null){
            storage = new Storage();
            storage.addDummyData();
        }
        return storage;
    }

    public  void addDummyData() {
        // Tilføjer dummy butikker, såfremt der ikke er nogle data i butikstabellen
        //SQLiteDatabase db = shoppingDatabaseHelper.getReadableDatabase();

       // Cursor c = db.rawQuery("SELECT * FROM SHOP", null);
        if (getShops().getCount() == 0) {

            insertShop("Rema 1000", "Møllevangs 10, 8210", "www.rema.dk");
            insertShop("Kvickly", "aabyhojvej 19, 8210", "www.kvickly.dk");
            insertShop("Bilka", "bilkavej 10, 8000","www.bilka.dk" );

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

    public ShopCursorWrapper getShops()
    {
        SQLiteDatabase db = shoppingDatabaseHelper.getReadableDatabase();
        Cursor cursor =  db.query("SHOP",
                new String[]{"_id", "NAME", "ADDRESS", "WEBSITE"},
                null, null, null, null, null, null);
        return new ShopCursorWrapper(cursor);
    }

    // PRoduct CRUD database Operations
    public static void insertProduct(String name, String volume){

        SQLiteDatabase db = shoppingDatabaseHelper.getWritableDatabase();
        ContentValues productValues = new ContentValues();
        productValues.put("NAME", name);
        productValues.put("VOLUME", volume);
        db.insert("PRODUCT", null, productValues);
    }

    public ShopCursorWrapper getProducts(){
        SQLiteDatabase db = shoppingDatabaseHelper.getReadableDatabase();
      Cursor cursor =  db.query("PRODUCT", new String[]{"_id", "NAME", "VOLUME"}, null, null, null, null, null);
    return new ShopCursorWrapper(cursor);
    }




}
