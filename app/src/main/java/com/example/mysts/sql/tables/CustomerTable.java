package com.example.mysts.sql.tables;

public class CustomerTable {
    public static final String TABLE_NAME = "customer_table";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
            "(" +
            Columns.CUST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            Columns.NAME + " text," +
            Columns.MOBILE + " text," +
            Columns.ADDRESS + " text," +
            Columns.CUST_CNT + " INTEGER" +
            ")";

    public static final class Columns {
        public static final String CUST_ID = "customerId",
                NAME = "name",
                MOBILE = "mobile",
                ADDRESS = "address",
                CUST_CNT = "cust_cnt";
    }

}

