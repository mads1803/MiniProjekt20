package com.example.madsstoltenborg.miniprojektforfra;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
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
}
