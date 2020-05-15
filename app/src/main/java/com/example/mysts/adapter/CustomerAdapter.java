package com.example.mysts.adapter;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.mysts.R;
import com.example.mysts.sql.tables.CustomerTable;

public class CustomerAdapter extends CursorAdapter {
    String TAG = "cursor data";

    public CustomerAdapter(Context context, Cursor cursor) {
        super(context, cursor, true);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.customer_list, null);
        return view;
    }

    @Override
    public void bindView(final View view, final Context context, Cursor cursor) {

        TextView tvname = view.findViewById(R.id.tvname);
        TextView tvmob = view.findViewById(R.id.tvmob);
        TextView tvaddress = view.findViewById(R.id.tvaddress);

        String name = cursor.getString(cursor.getColumnIndex(CustomerTable.Columns.NAME));
        String address = cursor.getString(cursor.getColumnIndex(CustomerTable.Columns.ADDRESS));
        String mobile = cursor.getString(cursor.getColumnIndex(CustomerTable.Columns.MOBILE));
        Log.d(TAG, "bindView: " + mobile);
        Log.d(TAG, "bindView: " + address);
        Log.d(TAG, "bindView: " + name);

        tvmob.setText("Mobile : +91" + mobile);
        tvaddress.setText("address : " + address);
        tvname.setText("Name :" + name);

    }
}
