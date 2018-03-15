package com.example.madsstoltenborg.miniprojektforfra;

import android.database.Cursor;
import android.database.CursorWrapper;

/**
 * Created by Mads Stoltenborg on 15-03-2018.
 */

public class ProductCursorWrapper extends CursorWrapper {
    public ProductCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Shop getProduct(){
        long id = getLong(getColumnIndex("_id"));
        String name = getString(getColumnIndex("NAME"));
        String address = getString(getColumnIndex("PRICE"));
        long shopid = getLong(getColumnIndex("SHOPID"));
        return new Product(id, name, address, shopid);
    }
}
