package com.example.madsstoltenborg.miniprojektforfra;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.support.design.widget.NavigationView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setBackgroundResource(R.color.colorPrimary);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawer,
                myToolbar,
                R.string.nav_open_drawer,
                R.string.nav_close_drawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        int id = item.getItemId();

        Intent intent = null;

        switch(id) {
            case R.id.nav_Product:
                showAddProductDialog();
                break;
            case R.id.nav_Shop:
                showAddShopDialog();
                break;
            case R.id.nav_GroceryList:
                showAddListDialog();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showAddListDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog_grocerylists, null);
        dialogBuilder.setView(dialogView);

        final EditText name = (EditText) dialogView.findViewById(R.id.dialogListName);



        dialogBuilder.setTitle("Add GroceryList");
        dialogBuilder.setMessage("Enter text below");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Storage.insertGroceryList(name.getText().toString());
                //do something with edt.getText().toString();
                Context context = getApplicationContext();
                CharSequence text = "You have added a grocerylist!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
                Context context = getApplicationContext();
                CharSequence text = "You have canceled!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }
    private void showAddShopDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog_shops, null);
        dialogBuilder.setView(dialogView);

        final EditText name = (EditText) dialogView.findViewById(R.id.dialogShopName);
        final EditText address = (EditText) dialogView.findViewById(R.id.dialogShopAddress);
        final EditText website = (EditText) dialogView.findViewById(R.id.dialogShopWebsite);


        dialogBuilder.setTitle("Add Product");
        dialogBuilder.setMessage("Enter text below");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Storage.insertShop(name.getText().toString(),address.getText().toString(), website.getText().toString());
                //do something with edt.getText().toString();
                Context context = getApplicationContext();
                CharSequence text = "You have added a shop!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
                Context context = getApplicationContext();
                CharSequence text = "You have canceled!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }
    private void showAddProductDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog_products, null);
        dialogBuilder.setView(dialogView);

        final EditText name = (EditText) dialogView.findViewById(R.id.dialogProductName);
        final EditText volume = (EditText) dialogView.findViewById(R.id.dialogProductVolume);


        dialogBuilder.setTitle("Add Product");
        dialogBuilder.setMessage("Enter text below");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                Storage.insertProduct(name.getText().toString(),volume.getText().toString());
                //do something with edt.getText().toString();
                Context context = getApplicationContext();
                CharSequence text = "You have added a product!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
                Context context = getApplicationContext();
                CharSequence text = "You have canceled!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
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
