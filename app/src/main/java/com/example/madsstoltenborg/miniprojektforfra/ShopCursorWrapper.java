package com.example.madsstoltenborg.miniprojektforfra;

import android.database.Cursor;
import android.database.CursorWrapper;

/**
 * Created by simon on 13-03-2018.
 */

public class ShopCursorWrapper extends CursorWrapper {
    public ShopCursorWrapper(Cursor cursor){ super(cursor); }

    public Shop getShop(){
        long id = getLong(getColumnIndex("_id"));
        String name = getString(getColumnIndex("NAME"));
        String address = getString(getColumnIndex("ADDRESS"));
        String website = getString(getColumnIndex("WEBSITE"));
        return new Shop(id, name, address, website);
    }
}
