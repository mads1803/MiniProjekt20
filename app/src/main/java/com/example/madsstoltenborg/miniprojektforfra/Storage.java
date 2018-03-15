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
        if(getProducts().getCount() == 0){
            insertProduct("Havregryn", "1KG");
            insertProduct("Mælk","1 L");
            insertProduct("Kage", "500 G");
            insertProduct("Ske", "1 stk");
        }

        if(getGroceryLists().getCount() == 0){
            insertGroceryList("Indkøbsliste 1");
            insertGroceryList("Indkøbsliste 2");
            insertGroceryList("Indkøbsliste 3");
        }

        if (getShopProducts().getCount() == 0){
            insertShopProduct(1,1, 20);
            insertShopProduct(2, 2, 30);
            insertShopProduct(3, 3, 40);
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

    //GroceryList crud

    public static void insertGroceryList(String name){
        SQLiteDatabase db = shoppingDatabaseHelper.getWritableDatabase();
        ContentValues groceryValues = new ContentValues();
        groceryValues.put("NAME", name);
        db.insert("GROCERYLISTS",null ,groceryValues);
         }

    public GroceryCursorWrapper getGroceryLists(){
        SQLiteDatabase db = shoppingDatabaseHelper.getReadableDatabase();
        Cursor cursor = db.query("GROCERYLISTS", new String[]{"_id", "NAME"}, null, null, null, null, null, null);
        return new GroceryCursorWrapper(cursor);
    }

    public static void insertShopProduct(int shop_id, int product_id, double price){
        SQLiteDatabase db = shoppingDatabaseHelper.getWritableDatabase();
        ContentValues shopProductValues = new ContentValues();
        shopProductValues.put("SHOP_ID", shop_id);
        shopProductValues.put("PRODUCT_ID", product_id);
        shopProductValues.put("PRICE", price);
        db.insert("SHOP_PRODUCTS", null, shopProductValues);
    }

    public static Cursor getShopProducts()
    {
        SQLiteDatabase db = shoppingDatabaseHelper.getReadableDatabase();
        return db.query("SHOP_PRODUCTS_VIEW", new String[]{"_id", "SHOP_ID", "PRODUCT_ID", "PRICE", "VOLUME", "SHOPNAME", "PRODUCTNAME" }, null, null, null, null, null);
    }


}
