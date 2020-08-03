package com.example.mysts.sql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.mysts.sql.tables.CustomerTable;
import com.example.mysts.sql.tables.OrderTable;
import com.example.mysts.sql.tables.ProductTable;
import com.example.mysts.sql.tables.SalesmanTable;
import com.example.mysts.sql.tables.TaskTable;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Admin on 9/23/2018.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static int version = 3;
    private static String dbname = "SalesmanDB";
    String TAG = "Error Message";

    public DBHelper(Context context) {
        super(context, dbname, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL(SalesmanTable.CREATE_TABLE);
            db.execSQL(CustomerTable.CREATE_TABLE);
            db.execSQL(ProductTable.CREATE_TABLE);
            db.execSQL(TaskTable.CREATE_TABLE);
            db.execSQL(OrderTable.CREATE_TABLE);
            // db.execSQL(TaskTable.CREATE_TABLE);
            /*db.execSQL(OtpTable.CREATE_TABLE);
             */
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e(TAG, "onCreate: ", e);
            e.printStackTrace();
        }
        db.endTransaction();
    }

    @Override
    public void onUpgrade(SQLiteDatabase dbname, int i, int i1) {
        Log.d(TAG, "onUpgrade: drop table");
        dbname.execSQL(" DROP TABLE IF EXISTS " + SalesmanTable.TABLE_NAME);
        dbname.execSQL(" DROP TABLE IF EXISTS " + CustomerTable.TABLE_NAME);
        dbname.execSQL(" DROP TABLE IF EXISTS " + ProductTable.TABLE_NAME);
        dbname.execSQL(" DROP TABLE IF EXISTS " + TaskTable.TABLE_NAME);
        dbname.execSQL(" DROP TABLE IF EXISTS " + OrderTable.TABLE_NAME);
        /*dbname.execSQL(" DROP TABLE IF EXISTS "+TaskTable.TABLE_NAME);
        dbname.execSQL(" DROP TABLE IF EXISTS "+OtpTable.TABLE_NAME);
       */
    }

    public Integer DeleteSalesmanOnId(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(SalesmanTable.TABLE_NAME, "salesmanId = ?", new String[]{id});

    }

    public Integer DeleteOrderOnId(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TaskTable.TABLE_NAME, "order_id = ?", new String[]{id});

    }


    public List<String> getAllLabels() {
        List<String> labels = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + CustomerTable.TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
                Log.d(TAG, "getAllLabels: new " + cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }


    public List<String> getAllProduct() {
        List<String> labels = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + ProductTable.TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
                Log.d(TAG, "getAllLabels: new " + cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }

    public List<String> getAllSalesman() {
        List<String> labels = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + SalesmanTable.TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }
    public List<String> getAllOrders() {
        List<String> labels = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  *  FROM " + OrderTable.TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        // returning lables
        return labels;
    }
}
