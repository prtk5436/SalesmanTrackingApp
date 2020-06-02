package com.example.mysts.sql.tables;

/**
 * Created by Admin on 9/23/2018.
 */
/*CREATE TABLE `` (
	`roll_no`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`name`	TEXT,
	`marks`	NUMERIC
);*/
public class SalesmanTable {
    public static final String TABLE_NAME = "salesman_table";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
            "(" +
            Columns.SALE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            Columns.NAME + " text," +
            Columns.MOBILE + " text," +
            Columns.EMAIL_ID + " text," +
            Columns.PASSWORD + " text," +
            Columns.SALE_CNT + " INTEGER" +
            ")";

    public static final class Columns {
        public static final String SALE_ID = "salesmanId",
                NAME = "name",
                MOBILE = "mobile",
                EMAIL_ID = "email_id",
                PASSWORD = "password",
                SALE_CNT = "sale_cnt";
    }


 /*  // public static final String TABLE_NAME = "book";
    public static final class Columns {
        public static final String ID = "salesmanId",
                NAME = "name",
                MOBILE = "mobile",
                EMAIL = "email_id",
                SALE_CNT = "sale_cnt";
    }

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME+
            "(" +
            Columns.ID+" integer primary key autoincrement," +
            Columns.NAME+" text," +
            Columns.EMAIL+" text," +
            Columns.SALE_CNT+" integer," +
            Columns.MOBILE +" integer" +
            ")";*/
}
