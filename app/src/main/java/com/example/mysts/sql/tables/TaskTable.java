package com.example.mysts.sql.tables;

public class TaskTable {
    public static final String TABLE_NAME = "task";

    public static final class Columns {
        public static final String ORDER_ID = "order_id",
                CUS_ID = "cus_id",
                SALE_ID = "sale_id",
                PRDT_ID= "product_id",
                TIME= "time",
                ORDER_CNT= "order_cnt";
    }
    public static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
            "(" +
            TaskTable.Columns.ORDER_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT," +
            TaskTable.Columns.CUS_ID+ " INTEGER REFERENCES "+ CustomerTable.TABLE_NAME + "("+CustomerTable.Columns.CUST_ID+"),"+
            TaskTable.Columns.SALE_ID+ " INTEGER REFERENCES "+ SalesmanTable.TABLE_NAME + "("+SalesmanTable.Columns.SALE_ID+"),"+
            TaskTable.Columns.PRDT_ID+ " INTEGER REFERENCES "+ ProductTable.TABLE_NAME + "("+ProductTable.Columns.PROD_ID+"),"+
            TaskTable.Columns.TIME+ " TEXT,"+
            TaskTable.Columns.ORDER_CNT+" INTEGER" +
            ")";
}
