package com.example.madsstoltenborg.miniprojektforfra;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Created by Mads Stoltenborg on 15-03-2018.
 */

public class ShopCursorAdapter extends SimpleCursorAdapter{
    ShopCursorAdapter(Context context, int layout, Cursor c,
                       String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        super.bindView(view, context, cursor);
        final int id = cursor.getInt(cursor.getColumnIndex("_id"));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, R.string.item_clicked, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ShopActivity.class);
                intent.putExtra(ShopActivity.EXTRA_SHOPID, (int) id);
                context.startActivity(intent);
            }
        });
    }
}
