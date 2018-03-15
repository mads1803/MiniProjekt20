package com.example.madsstoltenborg.miniprojektforfra;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by simon on 13-03-2018.
 */

public class ShoppingDBHelper extends SQLiteOpenHelper {


    private static final String DB_NAME = "Shopping";
    private static final int DB_VERSION = 2;
    private static Context applicationContext;
    private static ShoppingDBHelper shoppingDBHelper;

    public static void setApplicationContext(Context context){
        applicationContext = context.getApplicationContext();
    }

    public ShoppingDBHelper(Context context){
        super(context,DB_NAME, null, DB_VERSION);}

    public static ShoppingDBHelper getInstance(){
        if (shoppingDBHelper == null) {
            shoppingDBHelper = new ShoppingDBHelper(applicationContext);
        }
        return shoppingDBHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        updateMyDatabase(sqLiteDatabase,0,DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    updateMyDatabase(db, oldVersion, newVersion);
    }


    public void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion <= 1) {
            db.execSQL("CREATE TABLE SHOP ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "ADDRESS TEXT, "
                    + "WEBSITE TEXT);");

            db.execSQL("CREATE TABLE PRODUCT ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "VOLUME TEXT);");

            //tabel til bundne af shop og products
            db.execSQL("CREATE TABLE SHOP_PRODUCTS ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "SHOP_ID INTEGER, "
                    + "PRODUCT_ID INTEGER, "
                    + "PRICE NUMERIC);");

            db.execSQL("CREATE TABLE GROCERYLISTS ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEKST);");

            //Bought har jeg smidt ind for at kunne bruges som boolean evt
            db.execSQL("CREATE TABLE GROCERYLISTS_PRODUCTS ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "GROCERYLISTS_ID INTEGER, "
                    + "SHOP_PRODUCTS_ID INTEGER, "
                    + "BOUGHT INTEGER,"
                    + "QUANTITY INTEGER);");

            //TODO: View til shopproducts where productid=shopid
            //a = shopproducts, b = products, c = shops
            db.execSQL("CREATE VIEW SHOP_PRODUCTS_VIEW AS " +
                    "SELECT " +
                    "A._id AS _id, A.SHOP_ID AS SHOP_ID, A.PRODUCT_ID AS PRODUCT_ID, B.NAME AS PRODUCTNAME, B.VOLUME AS VOLUME, A.PRICE AS PRICE, C.NAME AS SHOPNAME " +
                    "FROM SHOP_PRODUCTS A, PRODUCT B, SHOP C " +
                    "WHERE A.PRODUCT_ID = B._id AND A.SHOP_ID = C._id;");


            //TODO: view til grocerylistsproduct m

        }

    }
}
