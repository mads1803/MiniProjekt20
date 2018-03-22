package com.example.madsstoltenborg.miniprojektforfra;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Created by Hansen on 15-03-2018.
 */

public class GroceryCursorAdapter extends SimpleCursorAdapter {
    GroceryCursorAdapter(Context context, int layout, Cursor c,
                         String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }

    //TODO Mangler at blive lavet.
    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        super.bindView(view, context, cursor);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox);


        final int id = cursor.getInt(cursor.getColumnIndex("_id"));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, R.string.item_clicked, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, GroceryListProducts.class);
                intent.putExtra(GroceryListProducts.EXTRA_DRINKID, (int) id);
                context.startActivity(intent);
            }
        });
    }
}
