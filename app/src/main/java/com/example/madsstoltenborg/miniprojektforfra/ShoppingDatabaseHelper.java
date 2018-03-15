package com.example.madsstoltenborg.miniprojektforfra;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mads Stoltenborg on 15-03-2018.
 */

public class ShoppingDatabaseHelper extends SQLiteOpenHelper{
    private static final String DB_NAME = "Shopping"; // the name of our database
    private static final int DB_VERSION = 2; // the version of the database
    private static Context applicationContext;
    private static ShoppingDatabaseHelper shoppingDatabaseHelper;

    private ShoppingDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    public static void setApplicationContext(Context context){
        applicationContext = context.getApplicationContext();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db, oldVersion, newVersion);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE DRINK (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "ADRESS TEXT);");
        }
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE DRINK ADD COLUMN FAVORITE NUMERIC;");
        }
    }
}
