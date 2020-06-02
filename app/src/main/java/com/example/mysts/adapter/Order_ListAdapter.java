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

import com.example.mysts.OrderDetailsActivity;
import com.example.mysts.R;
import com.example.mysts.sql.CustomerModel;
import com.example.mysts.sql.ProductModel;
import com.example.mysts.sql.SalesmanModel;
import com.example.mysts.sql.tables.CustomerTable;
import com.example.mysts.sql.tables.ProductTable;
import com.example.mysts.sql.tables.SalesmanTable;
import com.example.mysts.sql.tables.TaskTable;

import static android.content.ContentValues.TAG;

public class Order_ListAdapter extends CursorAdapter {
    // int count = 0;
    TextView tvsr_no, tvaddress, tvtime, tvSalesman;
    Button btnview_order;
    String address = "", time = "", mobile = "", salesman_name = "", cus_name = "", prdt_name = "";
    int order_id, cus_id, sale_id, prdt_id, prdt_price;

    public Order_ListAdapter(Context context, Cursor cursor) {
        super(context, cursor, true);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.order_list, null);
        return view;
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        Log.d(TAG, "bindView: cursor order adapter");
        tvsr_no = view.findViewById(R.id.tvsr_no);
        tvSalesman = view.findViewById(R.id.tvsalesman);
        tvaddress = view.findViewById(R.id.tvaddress);
        tvtime = view.findViewById(R.id.tvtime);
        btnview_order = view.findViewById(R.id.btnview_order);
        if (cursor != null) {
            Log.d(TAG, "bindView: cursor is not null");
            order_id = cursor.getInt(cursor.getColumnIndex(TaskTable.Columns.ORDER_ID));
            cus_id = cursor.getInt(cursor.getColumnIndex(TaskTable.Columns.CUS_ID));
            sale_id = cursor.getInt(cursor.getColumnIndex(TaskTable.Columns.SALE_ID));
            prdt_id = cursor.getInt(cursor.getColumnIndex(TaskTable.Columns.PRDT_ID));
            time = cursor.getString(cursor.getColumnIndex(TaskTable.Columns.TIME));
            Log.d(TAG, "getAllOrder:bindView cus_id" + cus_id);
            Log.d(TAG, "getAllOrder:bindView order_id" + order_id);
            Log.d(TAG, "getAllOrder:bindView sale_id" + sale_id);
            Log.d(TAG, "getAllOrder:bindView prdt_id" + prdt_id);
            Log.d(TAG, "getAllOrder:bindView time" + time);

            CustomerModel customerModel = new CustomerModel(context);
            Cursor cursor1 = customerModel.getCusDetailsOnId(cus_id);
            if (cursor1.moveToFirst()) {
                do {
                    cus_name = cursor1.getString(cursor1.getColumnIndex(CustomerTable.Columns.NAME));
                    address = cursor1.getString(cursor1.getColumnIndex(CustomerTable.Columns.ADDRESS));
                    mobile = cursor1.getString(cursor1.getColumnIndex(CustomerTable.Columns.MOBILE));
                    Log.d(TAG, "customerModel: cus_name" + cus_name);
                    Log.d(TAG, "customerModel: address" + address);
                    Log.d(TAG, "customerModel: mobile" + mobile);
                } while (cursor1.moveToNext());
            }

            SalesmanModel salesmanModel = new SalesmanModel(context);
            Cursor cursor3 = salesmanModel.getSaleDetailsOnId(sale_id);
            if (cursor3.moveToFirst()) {
                do {
                    salesman_name = cursor3.getString(cursor3.getColumnIndex(SalesmanTable.Columns.NAME));
                    Log.d(TAG, "SalesmanModel: salesman_name" + salesman_name);
                } while (cursor3.moveToNext());
            }

            ProductModel productModel = new ProductModel(context);
            Cursor cursor4 = productModel.getPrdtDetailsOnId(prdt_id);
            if (cursor4.moveToFirst()) {
                do {
                    prdt_name = cursor4.getString(cursor4.getColumnIndex(ProductTable.Columns.NAME));
                    prdt_price = cursor4.getInt(cursor4.getColumnIndex(ProductTable.Columns.PRICE));
                    Log.d(TAG, "ProductModel: prdt_price" + prdt_price);
                    Log.d(TAG, "ProductModel: prdt_name" + prdt_name);
                } while (cursor4.moveToNext());
            }


            tvaddress.setText("Location : " + address);
            tvSalesman.setText("Salesman : " + salesman_name);
            tvsr_no.setText("Order No." + order_id);
            tvtime.setText("Time : " + time);


        } else {
            Log.d(TAG, "bindView: Cursor is null");
        }

        btnview_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderDetailsActivity.class);
                intent.putExtra("order_no", order_id);
                intent.putExtra("time", time);
                intent.putExtra("cus_name", cus_name);
                intent.putExtra("address", address);
                intent.putExtra("mobile", mobile);
                intent.putExtra("prdt_name", prdt_name);
                intent.putExtra("prdt_price", prdt_price);
                intent.putExtra("salesman_name", salesman_name);
                context.startActivity(intent);

            }
        });
    }
}
