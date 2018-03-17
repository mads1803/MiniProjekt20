package com.example.madsstoltenborg.miniprojektforfra;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
                showAddListDialog();
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
}
