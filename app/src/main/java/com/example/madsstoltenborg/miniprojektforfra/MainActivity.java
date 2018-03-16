package com.example.madsstoltenborg.miniprojektforfra;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        if (doesDatabaseExist(getApplicationContext(), "Shopping")== false){
            Log.d("Demo", "SUCCCES");
        }


        //Create an OnItemClickListener
        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> listView,
                                            View itemView,
                                            int position,
                                            long id) {
                        if (position == 0) {
                            Intent intent = new Intent(MainActivity.this,
                                    ShopListActivity.class);
                            startActivity(intent);
                        }else if(position == 1){
                                Intent intent = new Intent(MainActivity.this,
                                        ProductListActivity.class);
                                startActivity(intent);
                        } else if(position == 2){
                            Intent intent = new Intent(MainActivity.this,
                                    GroceryListActivity.class);
                            startActivity(intent);
                        }
                    }
                };
        //Add the listener to the list view
        ListView listView = (ListView) findViewById(R.id.list_options);
        listView.setOnItemClickListener(itemClickListener);

        // Initialize DatabaseHelper
       ShoppingDBHelper.setApplicationContext(this);

    }

    //Checking if db exists
    private static boolean doesDatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }
//@Override
//    public boolean onCreateOptionsMenu(Menu menu){
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_products, menu);
//        return true;
//}
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_options:
//                // User chose the "Settings" item, show the app settings UI...
//                //TODO: Evt give en settings mulighed
//                return true;
//
//            case R.id.action_shop:
//                // User chose the "Favorite" action, mark the current item
//                // as a favorite...
//
//                return true;
//            case R.id.action_product:
//                // User chose the "Favorite" action, mark the current item
//                // as a favorite...
//                return true;
//            default:
//                // If we got here, the user's action was not recognized.
//                // Invoke the superclass to handle it.
//                return super.onOptionsItemSelected(item);
//
//        }
//    }

}
