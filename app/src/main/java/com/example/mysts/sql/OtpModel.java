package com.example.mysts.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mysts.sql.tables.CustomerTable;
import com.example.mysts.sql.tables.OtpTable;


public class OtpModel {
    SQLiteDatabase db;
    String TAG = "List items:";

    public OtpModel(Context context) {

        db = DBConnection.getDatabase(context);
    }

    public long addOtpDetails(int cus_id, String otp) {
        ContentValues values = new ContentValues();

        values.put(OtpTable.Columns.CUS_ID, cus_id);
        values.put(OtpTable.Columns.OTP, otp);
        return db.insert(OtpTable.TABLE_NAME, null, values);
    }

    public Cursor getCustomerDetails(String cusname) {
        Log.d(TAG, "getCustomerDetails: inside method");
        String name = "name";
        String[] columns = {"address", "mobile", "cus_Id"};
        Cursor cursor = db.query(CustomerTable.TABLE_NAME, columns, "name=?", new String[]{cusname}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String address = cursor.getString(cursor.getColumnIndex("address"));
                String mobile = cursor.getString(cursor.getColumnIndex("mobile"));
                int cus_id = cursor.getInt(cursor.getColumnIndex("cus_Id"));
                Log.d(TAG, "getCustomerDetails: address" + address);
                Log.d(TAG, "getCustomerDetails: mobile" + mobile);
                Log.d(TAG, "getCustomerDetails: cus_id" + cus_id);

            } while (cursor.moveToNext());
        }
        return cursor;
    }

    public Cursor getCustomerId(String cusname) {
        //  int  id = db.execSQL("select cus_Id from customer where name="+cusname);
        Cursor cursor = db.rawQuery("select cus_Id from customer where name=" + cusname, null);
        return cursor;
    }

    public Cursor getCusDetailsOnId(int cust_id) {
        String cus_Id = "cus_Id";
        String[] columns = {"address", "mobile", "name"};
        Cursor cursor = db.query(CustomerTable.TABLE_NAME, columns, "cus_Id=?", new String[]{cust_id + ""}, null, null, null);
        return cursor;
    }
}
