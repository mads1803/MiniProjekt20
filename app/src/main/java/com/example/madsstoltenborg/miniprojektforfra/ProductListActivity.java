package com.example.madsstoltenborg.miniprojektforfra;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
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

import org.w3c.dom.Text;

/**
 * Created by Mads Stoltenborg on 15-03-2018.
 */

public class ProductListActivity extends AppCompatActivity {
    private Cursor cursor;
    private SimpleCursorAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        ListView listProducts = (ListView) findViewById(R.id.list_products);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);

        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Products");
        try {
            cursor = Storage.getInstance().getProducts();
            listAdapter = new ProductCursorAdapter(this,
                    R.layout.product_list_item,
                    cursor,
                    new String[]{"NAME", "VOLUME"},
                    new int[]{R.id.productName, R.id.productVolume},
                    0);
            listProducts.setAdapter(listAdapter);
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "DATABASE UNAVILABLE", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
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
              showAddProductDialog();
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
    }


