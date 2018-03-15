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


    ShoppingDBHelper(Context context){
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
            db.execSQL("CREATE TABLE SHOP (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NAME TEXT, "
                    + "ADDRESS TEXT, "
                    + "WEBSITE TEXT);");
<<<<<<< HEAD:app/src/main/java/com/example/madsstoltenborg/miniprojektforfra/ShoppingDBHelper.java


=======
           // Insert("Rema 1000", "Møllevangs 10, 8210", "www.rema.dk");
           // addShop("Kvickly", "aabyhojvej 19, 8210", "www.kvickly.dk");
          //  addShop("Bilka", "bilkavej 10, 8000","www.bilka.dk" );
>>>>>>> 8e2542df16f0ef9ed492c99786ef9515df5ccec1:app/src/main/java/com/example/madsstoltenborg/miniprojektforfra/ShoppingHelper.java
        }

    }
}
