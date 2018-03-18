package com.example.madsstoltenborg.miniprojektforfra;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ShopListProducts extends AppCompatActivity {
    public static final String EXTRA_SHOPID = "shopId";
    private Cursor cursor;
    private SimpleCursorAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_list_products);

        try {
            //Get the drink from the intent
            int shopId = (Integer) getIntent().getExtras().get(EXTRA_SHOPID);

            Shop shop = Storage.getInstance().getShop(shopId);

            TextView name = (TextView) findViewById(R.id.name);
            name.setText(shop.getName());

            ListView listProducts = (ListView) findViewById(R.id.listShopProducts);




            Spinner spinner = (Spinner) findViewById(R.id.listDropDown);
// Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.spinnerTestData, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
            spinner.setAdapter(adapter);

            try {
                //TODO: GetShopProducts skal rettes og bruges i stedet for getProducts
                cursor = Storage.getInstance().getProducts();
                //TODO: Skal tilføje produktet til en indkøbsliste
                listAdapter = new ProductCursorAdapter(this,
                        R.layout.shopproduct_list_item,
                        cursor,
                        new String[]{"NAME", "VOLUME"}, //"PRICE"},
                        new int[]{R.id.productName2, R.id.productVolume2}, //TODO: bruges når getshopProducts er sat op og wrapperen er lavet , R.id.productPrice2
                        0);
                listProducts.setAdapter(listAdapter);
            } catch (SQLiteException e) {
                Toast toast = Toast.makeText(this, "DATABASE UNAVILABLE", Toast.LENGTH_SHORT);
                toast.show();
            }


        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

}
