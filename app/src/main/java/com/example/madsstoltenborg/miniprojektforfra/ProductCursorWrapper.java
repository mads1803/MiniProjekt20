package com.example.madsstoltenborg.miniprojektforfra;

import android.database.Cursor;

/**
 * Created by Mads Stoltenborg on 15-03-2018.
 */

public class ProductCursorWrapper {
    public ProductCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Shop getProduct(){
        long id = getLong(getColumnIndex("_id"));
        String name = getString(getColumnIndex("NAME"));
        String address = getString(getColumnIndex("ADDRESS"));
        String website = getString(getColumnIndex("WEBSITE"));
        return new Product(id, name, address, website);
    }
}
