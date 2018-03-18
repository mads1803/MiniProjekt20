package com.example.madsstoltenborg.miniprojektforfra;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

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

            try {
                //TODO: GetShopProducts skal rettes
                cursor = Storage.getInstance().getShopProducts();
                //TODO: Skal tilføje produktet til en indkøbsliste
                listAdapter = new ProductCursorAdapter(this,
                        R.layout.product_list_item,
                        cursor,
                        new String[]{"NAME", "VOLUME", "PRICE"},
                        new int[]{R.id.productName2, R.id.productVolume2, R.id.productPrice2},
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
