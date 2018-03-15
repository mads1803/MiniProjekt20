package com.example.madsstoltenborg.miniprojektforfra;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

}
