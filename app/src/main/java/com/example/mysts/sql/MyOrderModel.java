package com.example.mysts.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mysts.sql.tables.OrderTable;
import com.example.mysts.sql.tables.SalesmanTable;

public class MyOrderModel {
    SQLiteDatabase db;

    private static final String TAG = "Order Model";

    public MyOrderModel(Context context) {

        Log.d(TAG, "Inside MYOrderModel: ");
        db = DBConnection.getDatabase(context);
    }

    public long addMyOrder(String salesman_name, String customer_name, String customer_mobile, String order_time, String order_location, String product_name, String product_price, int order_cnt, String salesman_id) {
        Log.d(TAG, "addOrder:salesman_name " + salesman_name);
        Log.d(TAG, "addOrder:customer_name " + customer_name);
        Log.d(TAG, "addOrder:customer_mobile " + customer_mobile);
        Log.d(TAG, "addOrder:order_time " + order_time);
        Log.d(TAG, "addOrder:order_location " + order_location);
        Log.d(TAG, "addOrder:product_name " + product_name);
        Log.d(TAG, "addOrder:product_price " + product_price);
        Log.d(TAG, "addOrder:order_cnt " + order_cnt);
        Log.d(TAG, "addOrder:salesman_id " + salesman_id);
        ContentValues values = new ContentValues();
        values.put(OrderTable.Columns.SALESMAN_ID, salesman_id);
        values.put(OrderTable.Columns.SALESMAN_NAME, salesman_name);
        values.put(OrderTable.Columns.CUSTOMER_NAME, customer_name);
        values.put(OrderTable.Columns.CUSTOMER_MOBILE, customer_mobile);
        values.put(OrderTable.Columns.ORDER_TIME, order_time);
        values.put(OrderTable.Columns.PRODUCT_NAME, product_name);
        values.put(OrderTable.Columns.PRODUCT_PRICE, product_price);
        values.put(OrderTable.Columns.ORDER_LOCATION, order_location);
        values.put(OrderTable.Columns.ORDER_CNT, order_cnt);

        long id = db.insert(OrderTable.TABLE_NAME, null, values);

        return id;
    }

    public Cursor showOrderDetails() {
        Cursor cursor = db.rawQuery("select orderId, salesman_name, customer_name, customer_mobile, order_time,product_name,product_price,order_location ,order_cnt, salesman_id,orderId as _id  from order_table", null);
        // Cursor cursor = db.rawQuery("select *  from salesman_table", null);
        if (cursor.moveToFirst()) {
            do {
                Log.d(TAG, "showOrderDetails oderId: " + cursor.getString(1));
                Log.d(TAG, "showOrderDetails salesman_name: " + cursor.getString(2));
                Log.d(TAG, "showOrderDetails customer_name: " + cursor.getString(3));
                Log.d(TAG, "showOrderDetails customer_mobile: " + cursor.getString(4));
                Log.d(TAG, "showOrderDetails order_time: " + cursor.getString(5));
                Log.d(TAG, "showOrderDetails product_name: " + cursor.getString(6));
                Log.d(TAG, "showOrderDetails product_price: " + cursor.getString(7));
                Log.d(TAG, "showOrderDetails order_location: " + cursor.getString(8));
                Log.d(TAG, "showOrderDetails order_cnt: " + cursor.getString(9));
                Log.d(TAG, "showOrderDetails salesman_id: " + cursor.getString(10));
            } while (cursor.moveToNext());
        }
        return cursor;
    }

    public Cursor getOrderDetails(String salename) {
        Log.d(TAG, "getCustomerDetails: inside method");
        String[] columns = {"salesman_name", "salesman_id"};
        Cursor cursor = db.query(OrderTable.TABLE_NAME, columns, "salesman_name=?", new String[]{salename}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                //   String address = cursor.getString(cursor.getColumnIndex("address"));
                //    String mobile = cursor.getString(cursor.getColumnIndex("mobile"));
                int salesmanId = cursor.getInt(cursor.getColumnIndex("salesmanId"));
                // Log.d(TAG, "getCustomerDetails: address" + address);
                // Log.d(TAG, "getCustomerDetails: mobile" + mobile);
                Log.d(TAG, "getCustomerDetails: salesmanId" + salesmanId);

            } while (cursor.moveToNext());
        }
        return cursor;
    }

    public Cursor getOrderDetailsOnId(int sale_id) {

        Log.d(TAG, "Inside getOrderDetailsOnId: ");
        String orderId = "orderId";
        String[] columns = {"salesman_name", "customer_name", "customer_mobile", "order_time", "product_name", "product_price", "order_location", "order_cnt"};
        Cursor cursor = db.query(OrderTable.TABLE_NAME, columns, "salesman_id=?", new String[]{sale_id + ""}, null, null, null);
        return cursor;
    }

    public int incrementOrderCnt(int order_id) {
        int order_cnt = 0;
        Cursor cursor = db.rawQuery("select * from " + OrderTable.TABLE_NAME + " where " + OrderTable.Columns.ORDER_ID + "=" + order_id, null);
        if (cursor.moveToNext()) {
            order_cnt = cursor.getInt(cursor.getColumnIndex(OrderTable.Columns.ORDER_CNT));
            if (order_cnt <= 0) {
                Log.e("Salesman App", "Count is zero");
                return -1;
            }
        } else {
            Log.e("Salesman App", "Count is" + order_cnt);
            return -1;
        }
        try {
            db.beginTransaction();
            //  long id = db.insert(BookTransactionTable.TABLE_NAME, null, values);
            ContentValues valuesUpdate = new ContentValues();
            valuesUpdate.put(OrderTable.Columns.ORDER_CNT, order_cnt + 1);
            int affectedRows = db.update(OrderTable.TABLE_NAME, valuesUpdate, OrderTable.Columns.ORDER_ID + "=" + order_id, null);
            if (affectedRows == 1) {
                db.setTransactionSuccessful();
            } else {
                return -2;
            }
            db.endTransaction();
            return order_cnt;
        } catch (Exception e) {
            e.printStackTrace();
            if (db.inTransaction()) {
                db.endTransaction();
            }
        }
        return order_cnt;
    }
}

