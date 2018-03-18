package com.example.madsstoltenborg.miniprojektforfra;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Created by Mads Stoltenborg on 15-03-2018.
 */

public class GroceryProductListActivity extends AppCompatActivity {
    public static final String EXTRA_GROCERYID = "groceryListId";
    public static int groceryListId;
    private Cursor cursor;
    private SimpleCursorAdapter listAdapter;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_product_list);
        ListView listProducts = (ListView) findViewById(R.id.grocery_product_list);
        listProducts.setEmptyView(findViewById(R.id.grocer_product_emptylist));
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);


        groceryListId = (Integer) getIntent().getExtras().get(EXTRA_GROCERYID);


        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("GroceryProductList");

        SQLiteOpenHelper dbHelper = new ShoppingDBHelper(this);
        try {
            //TODO: get it ´to work HER ER FEJL / viewet lavet ikke og adapter virker ikke
            db = dbHelper.getReadableDatabase();
            // Sal have sendt id eller hentet id
            cursor = Storage.getInstance().getGrocerylistProducts(groceryListId);
            listAdapter = new ProductlistCursorAdapter(this,
                    R.layout.grocery_product_list_item,
                    cursor,
                    new String[]{"PRODUCTNAME", "QUANTITY", "VOLUME", "PRICE", "SHOPNAME"},
                    new int[]{R.id.grocery_productName, R.id.grocer_productQuantity, R.id.grocer_productVolume, R.id.grocer_productPrice, R.id.grocer_product_shop},
                    0);
            listProducts.setAdapter(listAdapter);
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "DATABASE UNAVILABLE", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_products, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_options:
                // User chose the "Settings" item, show the app settings UI...
                //TODO: Evt give en settings mulighed
                return true;

            case R.id.action_Addproduct:
                return true;
            case R.id.action_deleteProduct:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private class ProductlistCursorAdapter extends SimpleCursorAdapter {

        public ProductlistCursorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
            super(context, layout, c, from, to, flags);
        }


    }
}


