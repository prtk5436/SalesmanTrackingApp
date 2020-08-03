package com.example.mysts.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.mysts.R;
import com.example.mysts.sql.tables.ProductTable;

public class ProductAdapter extends CursorAdapter {
    String TAG = "cursor data";

    public ProductAdapter(Context context, Cursor cursor) {
        super(context, cursor, true);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.product_list, null);
        return view;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void bindView(final View view, final Context context, Cursor cursor) {

        TextView tvname = view.findViewById(R.id.tvname);
        TextView tvprice = view.findViewById(R.id.tvprice);


        String name = cursor.getString(cursor.getColumnIndex(ProductTable.Columns.NAME));
        String price = cursor.getString(cursor.getColumnIndex(ProductTable.Columns.PRICE));
        Log.d(TAG, "bindView: " + price);
        Log.d(TAG, "bindView: " + name);

        tvprice.setText("$ " + price);
        tvname.setText("" + name);
    }
}
