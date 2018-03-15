package com.example.madsstoltenborg.miniprojektforfra;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class GroceryListActivity extends AppCompatActivity {
    private Cursor cursor;
    private SimpleCursorAdapter listAdapter;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_list);
        ListView listOfGroceryLists = (ListView) findViewById(R.id.list_of_GroceryLists);
            try {
                cursor = Storage.getInstance().getGroceryLists();
                listAdapter = new ShopCursorAdapter(this,
                        R.layout.grocerylists_list_item,
                        cursor,
                        new String[]{"NAME"},
                        new int[]{R.id.GroceryName},
                        0);
                listOfGroceryLists.setAdapter(listAdapter);
            }
            catch (SQLiteException e){
                Toast toast = Toast.makeText(this, "DATABASE UNAVILABLE", Toast.LENGTH_SHORT);
                toast.show();
            }
    }
}
