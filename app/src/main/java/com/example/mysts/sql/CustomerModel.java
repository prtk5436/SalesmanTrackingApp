package com.example.mysts.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mysts.sql.tables.CustomerTable;

import java.util.ArrayList;
import java.util.List;

public class CustomerModel {
    private static final String TAG = "Customer Model";
    SQLiteDatabase db;

    public CustomerModel(Context context) {
        Log.d(TAG, "Inside CustomerModel: ");
        db = DBConnection.getDatabase(context);
    }

    public long addCustomer(String name, String address, String mobile, int cust_cnt) {
        ContentValues values = new ContentValues();
        values.put(CustomerTable.Columns.NAME, name);
        values.put(CustomerTable.Columns.ADDRESS, address);
        values.put(CustomerTable.Columns.MOBILE, mobile);
        values.put(CustomerTable.Columns.CUST_CNT, cust_cnt);

        long id = db.insert(CustomerTable.TABLE_NAME, null, values);
        return id;
    }

    public Cursor showCustomerDetails() {
        Cursor cursor = db.rawQuery("select name, address, mobile, cust_cnt, customerId as _id  from customer_table", null);
        // Cursor cursor = db.rawQuery("select *  from salesman_table", null);
        if (cursor.moveToFirst()) {
            do {

                Log.d(TAG, "showCustomerdetails name: " + cursor.getString(1));
                Log.d(TAG, "showCustomerdetails address " + cursor.getString(2));
                Log.d(TAG, "showCustomerdetails count: " + cursor.getString(3));
            } while (cursor.moveToNext());
        }
        return cursor;
    }




    public Cursor getCustomerDetails(String cusname) {
        Log.d(TAG, "getCustomerDetails: inside method");
        String[] columns = {"address", "mobile", "customerId"};
        Cursor cursor = db.query(CustomerTable.TABLE_NAME, columns, "name=?", new String[]{cusname}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String address = cursor.getString(cursor.getColumnIndex("address"));
                String mobile = cursor.getString(cursor.getColumnIndex("mobile"));
                int cus_id = cursor.getInt(cursor.getColumnIndex("customerId"));
                Log.d(TAG, "getCustomerDetails: address" + address);
                Log.d(TAG, "getCustomerDetails: mobile" + mobile);
                Log.d(TAG, "getCustomerDetails: cus_id" + cus_id);

            } while (cursor.moveToNext());
        }
        return cursor;
    }

    public Cursor getCusDetailsOnId(int cust_id) {
        String cus_Id = "customerId";
        String[] columns = {"address", "mobile", "name"};
        Cursor cursor = db.query(CustomerTable.TABLE_NAME, columns, "customerId=?", new String[]{cust_id+""}, null, null, null);
        return  cursor;

    }

}

