package com.example.mysts.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysts.R;
import com.example.mysts.Salesman;
import com.example.mysts.SalesmanDetailsActivity;
import com.example.mysts.sql.DBHelper;
import com.example.mysts.sql.tables.SalesmanTable;

public class SalesmanAdapter extends CursorAdapter {
    String TAG = "cursor data";
    int count = 0;

    DBHelper db;

    public SalesmanAdapter(Context context, Cursor cursor) {
        super(context, cursor, true);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.salesman_list, null);
        return view;
    }

    @Override
    public void bindView(final View view, final Context context, Cursor cursor) {

        db = new DBHelper(context);
        // TextView tvID = view.findViewById(R.id.tvID);
        TextView tvname = view.findViewById(R.id.tvname);
        TextView tvmob = view.findViewById(R.id.tvmob);
        //TextView tvemail = view.findViewById(R.id.tvemail);
        final EditText et_url = view.findViewById(R.id.et_url);

        ImageButton btn_delete = view.findViewById(R.id.delete);
        ImageButton i_btnview_salesman = view.findViewById(R.id.i_btnview_salesman);
        ImageButton btnget_location = view.findViewById(R.id.btnget_location);

        final int id = cursor.getInt(cursor.getColumnIndex(SalesmanTable.Columns.SALE_ID));
        final String name = cursor.getString(cursor.getColumnIndex(SalesmanTable.Columns.NAME));
        final String email = cursor.getString(cursor.getColumnIndex(SalesmanTable.Columns.EMAIL_ID));
        final String mobile = cursor.getString(cursor.getColumnIndex(SalesmanTable.Columns.MOBILE));
        final String password = cursor.getString(cursor.getColumnIndex(SalesmanTable.Columns.PASSWORD));
        Log.d(TAG, "bindView: " + mobile);
        Log.d(TAG, "bindView: " + email);
        Log.d(TAG, "bindView: " + name);
        Log.d(TAG, "bindView: " + id);
        Log.d(TAG, "bindView: password " + password);


        //tvID.setText("Salesman ID : "+id);
        tvmob.setText("" + mobile);
        //tvemail.setText("Email : "+email);
        tvname.setText("" + name);


        i_btnview_salesman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, SalesmanDetailsActivity.class);
                intent.putExtra("sale_name", name);
                intent.putExtra("sale_id", id);
                intent.putExtra("mobile", mobile);
                intent.putExtra("email", email);
                intent.putExtra("password", password);
                context.startActivity(intent);

                // context.startActivity(new Intent(context, OTPGenerationActivity.class));
            }
        });


        btnget_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String str_url = et_url.getText().toString().trim();
                Log.d(TAG, "bindView: link " + str_url);
                if (!TextUtils.isEmpty(str_url)) {

                    Log.d(TAG, "bindView:BTN " + str_url);
                    Uri gmmIntentUri = Uri.parse(str_url);
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    Log.d(TAG, "bindView:BTN " + str_url);
                    mapIntent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                    context.startActivity(mapIntent);

                    Log.d(TAG, "bindView:BTN " + gmmIntentUri);
                } else {
                    et_url.setError("enter link");

                }
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_id = String.valueOf(id);
                Integer deleterows = db.DeleteSalesmanOnId(str_id);
                if (deleterows == 0) {
                    Toast.makeText(context, "not deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, " Deleted >> ID :  " + str_id, Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(context, Salesman.class);
                    context.startActivity(i);
                }
            }
        });
    }
}
