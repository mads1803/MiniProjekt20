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


    public ShoppingDBHelper(Context context){
        super(context,DB_NAME, null, DB_VERSION);}

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

        }

    }
}
