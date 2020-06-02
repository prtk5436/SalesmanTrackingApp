package com.example.mysts.sql.tables;

public class ProductTable {
    public static final String TABLE_NAME = "product_table";
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
            "(" +
            ProductTable.Columns.PROD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            ProductTable.Columns.NAME + " text," +
            ProductTable.Columns.PRICE + " text," +
            ProductTable.Columns.PROD_CNT + " INTEGER" +
            ")";

    public static final class Columns {
        public static final String PROD_ID = "productId",
                NAME = "name",
                PRICE = "price",
                PROD_CNT = "prod_cnt";
    }

}
