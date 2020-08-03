package com.example.mysts.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.mysts.MyOrderDetailsActivity;
import com.example.mysts.R;
import com.example.mysts.sql.DBHelper;
import com.example.mysts.sql.tables.OrderTable;

public class OrderAdapter extends CursorAdapter {
    String TAG = "cursor data";
    int count = 0;

    DBHelper db;

    public OrderAdapter(Context context, Cursor cursor) {
        super(context, cursor, true);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.myorderlist, null);
        return view;
    }

    @Override
    public void bindView(final View view, final Context context, Cursor cursor) {

        db = new DBHelper(context);
        TextView orderno = view.findViewById(R.id.tvOrder_no);
        TextView tvsalesman = view.findViewById(R.id.tvsalesman);

        TextView tvsalesmanId = view.findViewById(R.id.tv_sales_id);
        Button btn_view = view.findViewById(R.id.btnview_order);

        final int id = cursor.getInt(cursor.getColumnIndex(OrderTable.Columns.ORDER_ID));

        final int sae_id = cursor.getInt(cursor.getColumnIndex(OrderTable.Columns.SALESMAN_ID));
        final String sale_name = cursor.getString(cursor.getColumnIndex(OrderTable.Columns.SALESMAN_NAME));
        final String cust_name = cursor.getString(cursor.getColumnIndex(OrderTable.Columns.CUSTOMER_NAME));
        final String cust_no = cursor.getString(cursor.getColumnIndex(OrderTable.Columns.CUSTOMER_MOBILE));
        final String prdt_name = cursor.getString(cursor.getColumnIndex(OrderTable.Columns.PRODUCT_NAME));
        final String prdt_price = cursor.getString(cursor.getColumnIndex(OrderTable.Columns.PRODUCT_PRICE));
        final String location = cursor.getString(cursor.getColumnIndex(OrderTable.Columns.ORDER_LOCATION));
        final String time = cursor.getString(cursor.getColumnIndex(OrderTable.Columns.ORDER_TIME));

        Log.d(TAG, "bindView: " + id);

        Log.d(TAG, "bindView: " + sae_id);
        Log.d(TAG, "bindView: " + sale_name);
        Log.d(TAG, "bindView: " + cust_name);
        Log.d(TAG, "bindView: " + cust_no);
        Log.d(TAG, "bindView: password " + prdt_name);
        Log.d(TAG, "bindView: password " + prdt_price);
        Log.d(TAG, "bindView: password " + location);
        Log.d(TAG, "bindView: password " + time);


        tvsalesmanId.setText("Salesman ID : "+sae_id);
        tvsalesman.setText("Salesman : " + sale_name);
        //tvemail.setText("Email : "+email);
        orderno.setText("Order ID : " + id);


        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, MyOrderDetailsActivity.class);

                intent.putExtra("orderId", id);
                intent.putExtra("salesman_name", sale_name);
                intent.putExtra("customer_name", cust_name);
                intent.putExtra("customer_mobile", cust_no);
                intent.putExtra("product_name", prdt_name);
                intent.putExtra("product_price", prdt_price);
                intent.putExtra("order_location", location);
                intent.putExtra("order_time", time);
                context.startActivity(intent);

                // context.startActivity(new Intent(context, OTPGenerationActivity.class));
            }
        });
    }
}
