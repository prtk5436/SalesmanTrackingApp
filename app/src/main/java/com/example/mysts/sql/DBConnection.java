package com.example.mysts.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Admin on 9/23/2018.
 */

public class DBConnection {

    private static SQLiteDatabase db;

    public static SQLiteDatabase getDatabase(Context context) {
        if (db == null) {
            db = new DBHelper(context).getWritableDatabase();
        }

        return db;
    }


}
