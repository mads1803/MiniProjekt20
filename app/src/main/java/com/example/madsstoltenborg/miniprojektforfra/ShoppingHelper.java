package com.example.madsstoltenborg.miniprojektforfra;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by simon on 13-03-2018.
 */

public class ShoppingHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 2;
    private static final String DB_NAME = "shopping";

    ShoppingHelper(Context context){
        super(context,DB_NAME, null, DB_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
updateMyDatabase(sqLiteDatabase,0,DB_VERSION);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    updateMyDatabase(db,oldVersion,newVersion);
    }


    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE SHOP (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "ADDRESS TEXT, "
                    + "WEBSITE TEXT);");
           // Insert("Rema 1000", "Møllevangs 10, 8210", "www.rema.dk");
           // addShop("Kvickly", "aabyhojvej 19, 8210", "www.kvickly.dk");
          //  addShop("Bilka", "bilkavej 10, 8000","www.bilka.dk" );
        }
//        if (oldVersion < 2) {
//            db.execSQL("ALTER TABLE SHOP ADD COLUMN OPEN BOOLEAN;");
//        }
    }
}
