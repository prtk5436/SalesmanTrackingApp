package com.example.mysts.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import com.example.mysts.sql.tables.TaskTable;

import static android.content.ContentValues.TAG;

public class OrderModel {
    SQLiteDatabase db;

    public OrderModel(Context context) {

        db = DBConnection.getDatabase(context);
    }

    public long addOrder(int cus_id, int sale_id, int product_id, String time, int order_cnt) {
        Log.d(TAG, "addOrder:cus_id "+cus_id);
        Log.d(TAG, "addOrder:sale_id "+product_id);
        Log.d(TAG, "addOrder:prdt_id "+sale_id);
        Log.d(TAG, "addOrder:time "+time);
        Log.d(TAG, "addOrder:order_cnt "+order_cnt);
        ContentValues values = new ContentValues();
        values.put(TaskTable.Columns.CUS_ID, cus_id);
        values.put(TaskTable.Columns.PRDT_ID,product_id);
        values.put(TaskTable.Columns.SALE_ID, sale_id);
        values.put(TaskTable.Columns.TIME,time);
        values.put(TaskTable.Columns.ORDER_CNT, order_cnt);

        long id = db.insert(TaskTable.TABLE_NAME, null, values);

        return id;
    }

    public Cursor getAllOrder() {
        Log.d(TAG, "getAllOrder: Inside Method");
        Cursor cursor = db.rawQuery("select order_id, cus_id, sale_id, product_id, time, order_cnt, order_id as _id  from task", null);
        if ( cursor != null && cursor.moveToFirst()) {
            do {

                int order_id = cursor.getInt(cursor.getColumnIndex(TaskTable.Columns.ORDER_ID));
                int cus_id  =cursor.getInt(cursor.getColumnIndex(TaskTable.Columns.CUS_ID));
                int sale_id = cursor.getInt(cursor.getColumnIndex(TaskTable.Columns.SALE_ID));
                int prdt_id = cursor.getInt(cursor.getColumnIndex(TaskTable.Columns.PRDT_ID));
                String time= cursor.getString(cursor.getColumnIndex(TaskTable.Columns.TIME));

                int order_cnt = cursor.getInt(cursor.getColumnIndex(TaskTable.Columns.ORDER_CNT));

                Log.d(TAG, "getAllOrder: cus_id"+ cus_id);
                Log.d(TAG, "getAllOrder: sale_id"+ sale_id);
                Log.d(TAG, "getAllOrder: prdt_id"+ prdt_id);
                Log.d(TAG, "getAllOrder: time"+ time);

                Log.d(TAG, "getAllOrder: oderId"+ order_id);
                Log.d(TAG, "getAllOrder: count"+ order_cnt);



            } while (cursor.moveToNext());
        }
        return cursor;
    }


    public Cursor getOrderDetailsOnsaleId(int sale_id) {
      /*  String salesmanId = "salesmanId";
        String[] columns = {"cus_id", "prdt_id", "time",};
        Cursor cursor = db.query(TaskTable.TABLE_NAME, columns, "sale_id=?", new String[]{sale_id+""}, null, null, null);
        return  cursor;*/

        return db.rawQuery(
                "select order_id as _id, cus_id, product_id" +
                        " from task" +
                        " where sale_id="+sale_id+"" , null);
    }




   /* public Cursor getAllSalesman() {
        //Cursor cursor = db.rawQuery("select cus_id, sale_id, time, order_id as _id  from" +OrderTable.TABLE_NAME , null);
        Cursor cursor= db.rawQuery("select cus_id, sale_id, prdt_id, oder_id as _id from order", null);
        return cursor;
    }*/
}
