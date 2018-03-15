package com.example.madsstoltenborg.miniprojektforfra;

import android.app.Activity;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Mads Stoltenborg on 15-03-2018.
 */

public class ShopActivity extends Activity {
    public static final String EXTRA_SHOPID = "shopId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        try {
            //Get the drink from the intent
            int shopId = (Integer) getIntent().getExtras().get(EXTRA_SHOPID);

            Shop shop = Storage.getInstance().getShop(shopId);

            TextView name = (TextView) findViewById(R.id.name);
            name.setText(shop.getName());

            TextView address = (TextView) findViewById(R.id.address);
            address.setText(shop.getAddress());

            TextView website = (TextView) findViewById(R.id.website);
            website.setText(shop.getWebsite());

        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

    }
}
