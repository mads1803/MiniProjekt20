package com.example.madsstoltenborg.miniprojektforfra;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Created by Mads Stoltenborg on 15-03-2018.
 */

public class ProductListActivity extends Activity {
    private Cursor cursor;
    private SimpleCursorAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ListView listProducts = (ListView) findViewById(R.id.list_products);

        try {
            cursor = Storage.getInstance().getProducts();
            listAdapter = new ProductCursorAdapter(this,
                    R.layout.product_list_item,
                    cursor,
                    new String[]{"NAME", "VOLUME"},
                    new int[]{R.id.productName, R.id.productVolume},
                    0);
            listProducts.setAdapter(listAdapter);
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "DATABASE UNAVILABLE", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
