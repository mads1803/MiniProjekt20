package com.example.madsstoltenborg.miniprojektforfra;

import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Grocery Lists");
            try {
                cursor = Storage.getInstance().getGroceryLists();
                listAdapter = new GroceryCursorAdapter(this,
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_grocerylists, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_options:
                // User chose the "Settings" item, show the app settings UI...
                //TODO: Evt give en settings mulighed
                return true;

            case R.id.action_AddGroceryList:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...

                return true;
            case R.id.action_deleteGroceryList:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}
