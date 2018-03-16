package com.example.madsstoltenborg.miniprojektforfra;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.List;

public class ShopListActivity extends AppCompatActivity {
    private Cursor cursor;
   // private SQLiteDatabase db;
    private SimpleCursorAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list);
        ListView listShops = (ListView) findViewById(R.id.list_shops);
        ListView listProducts = (ListView) findViewById(R.id.list_products);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Shops");

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_shops, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_options:
                // User chose the "Settings" item, show the app settings UI...
                //TODO: Evt give en settings mulighed
                return true;

            case R.id.action_AddShop:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...

                return true;
            case R.id.action_deleteShop:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cursor.close();
    }
}
