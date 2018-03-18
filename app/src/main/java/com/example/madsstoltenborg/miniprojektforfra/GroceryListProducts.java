package com.example.madsstoltenborg.miniprojektforfra;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GroceryListProducts extends AppCompatActivity {
    public static final String EXTRA_DRINKID = "drinkId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list_products);
    }
}
