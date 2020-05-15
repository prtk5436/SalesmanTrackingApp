package com.example.mysts.sql.tables;

public class OtpTable {
    public static final String TABLE_NAME = "OTP_Table";
    public static final class Columns {
        public static final String OTP_ID = "otp_id",
                CUS_ID = "cus_id",
                OTP = "otp";
    }

    public static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
            "(" +
            OtpTable.Columns.OTP_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT," +
            OtpTable.Columns.CUS_ID+ " INTEGER REFERENCES "+ CustomerTable.TABLE_NAME + "("+CustomerTable.Columns.CUST_ID+"),"+
            Columns.OTP+" TEXT" +
            ")";
}
