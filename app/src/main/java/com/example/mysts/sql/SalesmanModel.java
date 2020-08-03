/*
package mayuri.softcrowd.com.example.salesmantrackingapp.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import mayuri.softcrowd.com.example.salesmantrackingapp.sql.tables.ProductTable;
import mayuri.softcrowd.com.example.salesmantrackingapp.sql.tables.SalesmanTable;


public class SalesmanModel {
    private static final String TAG = "Salesman Model";
    SQLiteDatabase db;

    public SalesmanModel(Context context) {

        db = DBConnection.getDatabase(context);
    }

    public long addSalesman(String name,String email, String password, String mobile,  int sale_cnt) {
        ContentValues values = new ContentValues();
        values.put(SalesmanTable.Columns.NAME, name);
        values.put(SalesmanTable.Columns.EMAIL_ID,email);
        values.put(SalesmanTable.Columns.MOBILE, mobile);
        values.put(SalesmanTable.Columns.PASSWORD, password);
        values.put(SalesmanTable.Columns.SALE_CNT,sale_cnt);

        long id = db.insert(SalesmanTable.TABLE_NAME, null, values);
        return id;
    }

    public Cursor showSalesmanDetails() {
        Cursor cursor = db.rawQuery("select name, email_id, mobile, password, sale_cnt, salesmanId as _id  from salesman_table", null);
       // Cursor cursor = db.rawQuery("select *  from salesman_table", null);
        if (cursor.moveToFirst()) {
            do {

                Log.d(TAG, "showSalesmanDetails name: "+cursor.getString(1));
                Log.d(TAG, "showSalesmanDetails email: "+cursor.getString(2));
                Log.d(TAG, "showSalesmanDetails password: "+cursor.getString(3));
                Log.d(TAG, "showSalesmanDetails count: "+cursor.getString(4));
            } while (cursor.moveToNext());
        }
        return cursor;
    }

    public List<String> getAllSalesman(){
        List<String> labels = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + SalesmanTable.TABLE_NAME;

        //SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
       // db.close();

        // returning lables
        return labels;
    }
    public Cursor getSalesmanId(String salename) {
        //  int  id = db.execSQL("select cus_Id from customer where name="+cusname);
        Cursor cursor = db.rawQuery("select salesmanId from salesman_table where name="+salename, null);
        return cursor;
    }

*/
/*
    public void printBookTable() {
        Cursor cursor = db.rawQuery("select * from book", null);

        int count = cursor.getColumnCount();
        Log.e("LibraryApp", "Number of columns : "+count);
        StringBuffer buffer = new StringBuffer();
        for(int i=0;i<count;i++) {
            String columnName = cursor.getColumnName(i);
            buffer.append(columnName+" - ");
        }
        Log.e("LibraryApp", buffer.toString());

        while(cursor.moveToNext()) {
            StringBuffer row = new StringBuffer();
            for(int i=0;i<count;i++) {
                String value = cursor.getString(i);
                row.append(value+" - ");
            }
            Log.e("LibraryApp", row.toString());
        }
    }*//*

}
*/


package com.example.mysts.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mysts.sql.tables.CustomerTable;
import com.example.mysts.sql.tables.SalesmanTable;

import java.util.ArrayList;
import java.util.List;


public class SalesmanModel {
    private static final String TAG = "Salesman Model";
    SQLiteDatabase db;

    public SalesmanModel(Context context) {
        Log.d(TAG, "Inside SalesmanModel: ");
        db = DBConnection.getDatabase(context);
    }

    public long addSalesman(String name, String email, String password, String mobile, int sale_cnt) {
        ContentValues values = new ContentValues();
        values.put(SalesmanTable.Columns.NAME, name);
        values.put(SalesmanTable.Columns.EMAIL_ID, email);
        values.put(SalesmanTable.Columns.MOBILE, mobile);
        values.put(SalesmanTable.Columns.PASSWORD, password);
        values.put(SalesmanTable.Columns.SALE_CNT, sale_cnt);

        long id = db.insert(SalesmanTable.TABLE_NAME, null, values);
        return id;
    }


    public Cursor showSalesmanDetails() {
        Cursor cursor = db.rawQuery("select salesmanId, name, email_id, mobile, password, sale_cnt, salesmanId as _id  from salesman_table", null);
        // Cursor cursor = db.rawQuery("select *  from salesman_table", null);
        if (cursor.moveToFirst()) {
            do {
                Log.d(TAG, "showSalesmanDetails id: " + cursor.getString(1));
                Log.d(TAG, "showSalesmanDetails name: " + cursor.getString(2));
                Log.d(TAG, "showSalesmanDetails email: " + cursor.getString(3));
                Log.d(TAG, "showSalesmanDetails password: " + cursor.getString(4));
                Log.d(TAG, "showSalesmanDetails count: " + cursor.getString(5));
            } while (cursor.moveToNext());
        }
        return cursor;
    }


    public List<String> getAllSalesman() {
        List<String> labels = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + SalesmanTable.TABLE_NAME;

        //SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                labels.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        // db.close();

        // returning lables
        return labels;
    }
/*
    public Cursor getSalesmanId(String salename) {
        Log.d(TAG, "getSalesmanId: inside method");
        String name = "name";
        String[] columns = {"salesmanId"};
        Cursor cursor = db.query(SalesmanTable.TABLE_NAME, columns, "name=?", new String[]{salename}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int salesman_id = cursor.getInt(cursor.getColumnIndex("salesmanId"));
                Log.d(TAG, "getSalesmanId: salesmanId" + salesman_id);

            } while (cursor.moveToNext());
        }
        return cursor;
    }*/

    public Cursor getSalesmanDetails(String salename) {
        Log.d(TAG, "getCustomerDetails: inside method");
        String[] columns = {"name", "mobile", "salesmanId"};
        Cursor cursor = db.query(SalesmanTable.TABLE_NAME, columns, "name=?", new String[]{salename}, null, null, null);
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
    public Cursor getSaleDetailsOnId(int sales_id) {
        Log.d(TAG, "Inside getSaleDetailsOnId: ");
        String salesmanId = "salesmanId";
        String[] columns = {"name", "email_id", "mobile", "password", "sale_cnt"};
        Cursor cursor = db.query(SalesmanTable.TABLE_NAME, columns, "salesmanId=?", new String[]{sales_id + ""}, null, null, null);
        return cursor;
    }

    public int incrementSaleCnt(int sale_id) {
        int sale_cnt = 0;
        Cursor cursor = db.rawQuery("select * from " + SalesmanTable.TABLE_NAME + " where " + SalesmanTable.Columns.SALE_ID + "=" + sale_id, null);
        if (cursor.moveToNext()) {
            sale_cnt = cursor.getInt(cursor.getColumnIndex(SalesmanTable.Columns.SALE_CNT));
            if (sale_cnt <= 0) {
                Log.e("Salesman App", "Count is zero");
                return -1;
            }
        } else {
            Log.e("Salesman App", "Count is" + sale_cnt);
            return -1;
        }
        try {
            db.beginTransaction();
            //  long id = db.insert(BookTransactionTable.TABLE_NAME, null, values);
            ContentValues valuesUpdate = new ContentValues();
            valuesUpdate.put(SalesmanTable.Columns.SALE_CNT, sale_cnt + 1);
            int affectedRows = db.update(SalesmanTable.TABLE_NAME, valuesUpdate, SalesmanTable.Columns.SALE_ID + "=" + sale_id, null);
            if (affectedRows == 1) {
                db.setTransactionSuccessful();
            } else {
                return -2;
            }
            db.endTransaction();
            return sale_cnt;
        } catch (Exception e) {
            e.printStackTrace();
            if (db.inTransaction()) {
                db.endTransaction();
            }
        }
        return sale_cnt;
    }
}

