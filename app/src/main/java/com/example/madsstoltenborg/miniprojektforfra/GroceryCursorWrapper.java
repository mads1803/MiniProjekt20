package com.example.madsstoltenborg.miniprojektforfra;

import android.database.Cursor;
import android.database.CursorWrapper;

/**
 * Created by Hansen on 15-03-2018.
 */

public class GroceryCursorWrapper extends CursorWrapper {
    public GroceryCursorWrapper(Cursor cursor) {super(cursor);}

    public GroceryList getGroceryLists(){
        long id = getLong(getColumnIndex("_id"));
        String name = getString(getColumnIndex("NAME"));
        return new GroceryList(id, name);
    }
}
