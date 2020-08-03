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
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.mysts.MyOrderDetailsActivity;
import com.example.mysts.OTPGenerationActivity;
import com.example.mysts.R;
import com.example.mysts.sql.DBHelper;
import com.example.mysts.sql.tables.OrderTable;

import static android.content.ContentValues.TAG;

public class Salesman_Task_Adapter extends CursorAdapter {
    Button btn_done1, btn_generateOTP;
    ImageButton btnview_order;
    DBHelper db;
    public Salesman_Task_Adapter(Context context, Cursor cursor) {
        super(context, cursor, true);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.salesman_task_list, null);
        Log.d(TAG, "newView: sale task adapter ");
        return view;
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        Log.d(TAG, "bindView: ");
        db = new DBHelper(context);
        btnview_order = view.findViewById(R.id.i_btnview_order);
        TextView tvsale_name = (TextView) view.findViewById(R.id.tvsale_name);
        TextView tvaddress = (TextView) view.findViewById(R.id.tvaddress);
        TextView tvtime = (TextView) view.findViewById(R.id.tvtime);
        btn_done1 = (Button) view.findViewById(R.id.btn_done1);
        btn_generateOTP = view.findViewById(R.id.btn_generateOTP);
        TextView tv_sales_id = view.findViewById(R.id.tv_sales_id);

        final int id = cursor.getInt(cursor.getColumnIndex(OrderTable.Columns.ORDER_ID));
        final int sale_id = cursor.getInt(cursor.getColumnIndex(OrderTable.Columns.SALESMAN_ID));
        final String sale_name = cursor.getString(cursor.getColumnIndex(OrderTable.Columns.SALESMAN_NAME));
        final String cust_name = cursor.getString(cursor.getColumnIndex(OrderTable.Columns.CUSTOMER_NAME));
        final String cust_no = cursor.getString(cursor.getColumnIndex(OrderTable.Columns.CUSTOMER_MOBILE));
        final String prdt_name = cursor.getString(cursor.getColumnIndex(OrderTable.Columns.PRODUCT_NAME));
        final String prdt_price = cursor.getString(cursor.getColumnIndex(OrderTable.Columns.PRODUCT_PRICE));
        final String location = cursor.getString(cursor.getColumnIndex(OrderTable.Columns.ORDER_LOCATION));
        final String time = cursor.getString(cursor.getColumnIndex(OrderTable.Columns.ORDER_TIME));
        Log.d(TAG, "bindView: " + id);
        Log.d(TAG, "bindView: " + sale_id);
        Log.d(TAG, "bindView: " + sale_name);
        Log.d(TAG, "bindView: " + cust_name);
        Log.d(TAG, "bindView: " + cust_no);
        Log.d(TAG, "bindView:  " + prdt_name);
        Log.d(TAG, "bindView:  " + prdt_price);
        Log.d(TAG, "bindView:  " + location);
        Log.d(TAG, "bindView:  " + time);

        Log.d(TAG, "bindView: cursor order adapter");
//       tvorderId.setText("" + id);
        tvaddress.setText("Location : " + location);
        tvsale_name.setText("" + sale_name);
        tvtime.setText("Time:" + time);
        tv_sales_id.setText("Salesman : "+sale_id);

        btnview_order.setOnClickListener(new View.OnClickListener() {
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

        btn_done1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OTPGenerationActivity.class);
                intent.putExtra("orderId", id);
                intent.putExtra("customer_name", cust_name);
                intent.putExtra("customer_mobile", cust_no);
                context.startActivity(intent);
                // context.startActivity(new Intent(context, OTPGenerationActivity.class));
            }
        });
    }
}

