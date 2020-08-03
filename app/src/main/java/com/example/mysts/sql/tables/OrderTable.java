package com.example.mysts.sql.tables;

public class OrderTable {
    public static final String TABLE_NAME = "order_table";

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
            "(" +
            OrderTable.Columns.ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            OrderTable.Columns.SALESMAN_NAME + " text," +
            OrderTable.Columns.CUSTOMER_NAME + " text," +
            OrderTable.Columns.CUSTOMER_MOBILE + " text," +
            OrderTable.Columns.ORDER_TIME + " text," +
            OrderTable.Columns.ORDER_LOCATION + " text," +
            OrderTable.Columns.PRODUCT_NAME + " text," +
            OrderTable.Columns.PRODUCT_PRICE + " text," +
            OrderTable.Columns.ORDER_CNT + " INTEGER," +
            OrderTable.Columns.SALESMAN_ID + " text" +
            ")";

    public static final class Columns {
        public static final String ORDER_ID = "orderId",
                SALESMAN_NAME = "salesman_name",
                CUSTOMER_NAME = "customer_name",
                CUSTOMER_MOBILE = "customer_mobile",
                ORDER_TIME = "order_time",
                ORDER_LOCATION = "order_location",
                PRODUCT_NAME = "product_name",
                PRODUCT_PRICE = "product_price",
                ORDER_CNT = "order_cnt",
                SALESMAN_ID = "salesman_id";
    }
}


