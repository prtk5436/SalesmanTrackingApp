package com.example.mysts.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mysts.sql.tables.ProductTable;

public class ProductModel {
    private static final String TAG = "Product Model";
    SQLiteDatabase db;

    public ProductModel(Context context) {
        Log.d(TAG, "Inside ProductModel: ");
        db = DBConnection.getDatabase(context);
    }

    public long addProduct(String name, String price, int prod_cnt) {
        ContentValues values = new ContentValues();
        values.put(ProductTable.Columns.NAME, name);
        values.put(ProductTable.Columns.PRICE, price);
        values.put(ProductTable.Columns.PROD_CNT, prod_cnt);

        long id = db.insert(ProductTable.TABLE_NAME, null, values);
        return id;
    }


    public Cursor showProductDetails() {
        Cursor cursor = db.rawQuery("select name, price, prod_cnt, productId as _id  from product_table", null);
        // Cursor cursor = db.rawQuery("select *  from salesman_table", null);
        if (cursor.moveToFirst()) {
            do {

                Log.d(TAG, "showProductDetails name: " + cursor.getString(1));
                Log.d(TAG, "showProductDetails price: " + cursor.getString(2));
                Log.d(TAG, "showSalesmanDetails count: " + cursor.getString(3));
            } while (cursor.moveToNext());
        }
        return cursor;
    }


    public Cursor getProductDetails(String prdtname) {
        String name = "name";
        String[] columns = {"price", "productId"};
        Cursor cursor = db.query(ProductTable.TABLE_NAME, columns, "name=?", new String[]{prdtname}, null, null, null);
        int price = 0, prdt_id = 0;
        if (cursor.moveToFirst()) {
            do {
                price = cursor.getInt(cursor.getColumnIndex(ProductTable.Columns.PRICE));
                prdt_id = cursor.getInt(cursor.getColumnIndex(ProductTable.Columns.PROD_ID));
                Log.d(TAG, "getProductDetails: " + price);
            } while (cursor.moveToNext());
        }
        return cursor;
    }

    public Cursor getPrdtDetailsOnId(int prdt_id) {

        Log.d(TAG, "Inside getPrdtDetailsOnId: ");
        String productId = "productId";
        String[] columns = {"price", "name"};
        Cursor cursor = db.query(ProductTable.TABLE_NAME, columns, "productId=?", new String[]{prdt_id + ""}, null, null, null);
        return cursor;
    }

}

