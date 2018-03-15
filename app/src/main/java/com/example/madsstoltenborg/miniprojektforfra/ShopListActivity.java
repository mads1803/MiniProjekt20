package com.example.madsstoltenborg.miniprojektforfra;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.List;

public class ShopListActivity extends Activity {
    private Cursor cursor;
   // private SQLiteDatabase db;
    private SimpleCursorAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);
        ListView listShops = (ListView) findViewById(R.id.list_shops);
       // SQLiteOpenHelper shoppingDBHelper = new ShoppingDBHelper(this);

        try {
         //   db = shoppingDBHelper.getReadableDatabase();
            cursor = Storage.getInstance().getShops();
            listAdapter = new ShopCursorAdapter(this,
                    R.layout.shop_list_item,
                    cursor,
                    new String[]{"NAME", "ADDRESS", "WEBSITE"},
                    new int[]{R.id.shopName, R.id.shopAddress, R.id.shopWebsite},
                    0);
            listShops.setAdapter(listAdapter);
        }
        catch (SQLiteException e){
            Toast toast = Toast.makeText(this, "DATABASE UNAVILABLE", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    /*@Override
    protected void onRestart() {
        super.onRestart();
        cursor = Storage.getInstance().getShops();
        listAdapter.changeCursor(cursor);
    }*/

    @Override
    public void onDestroy() {
        super.onDestroy();
        cursor.close();
    }
}
