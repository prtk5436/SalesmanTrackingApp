package com.example.mysts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class OrderDetailsActivity extends AppCompatActivity {

    String cus_name = "", mobile = "", prdt_name = "", salesman_name = "", address = "", time = "";
    int prdt_price = 0, orderno = 0;
    TextView tvsale_name, tvprice, tvproduct, tv_mob, tv_address, tvcus_name, tv_time, tv_orderno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        tv_orderno = findViewById(R.id.tvOrderno);
        tv_time = findViewById(R.id.tv_time);
        tvsale_name = (TextView) findViewById(R.id.tvsale_name);
        tvprice = (TextView) findViewById(R.id.tvprice);
        tvproduct = (TextView) findViewById(R.id.tvproduct);
        tv_mob = (TextView) findViewById(R.id.tv_mob);
        tv_address = (TextView) findViewById(R.id.tv_address);
        tvcus_name = (TextView) findViewById(R.id.tvcus_name);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            orderno = extras.getInt("order_no");
            time = extras.getString("time");
            cus_name = extras.getString("cus_name");
            address = extras.getString("address");
            mobile = extras.getString("mobile");
            prdt_name = extras.getString("prdt_name");
            prdt_price = extras.getInt("prdt_price");
            salesman_name = extras.getString("salesman_name");
        }

        tv_time.setText("" + time);
        tv_orderno.setText("" + orderno);
        tvsale_name.setText("" + salesman_name);
        tvprice.setText("" + prdt_price);
        tvproduct.setText("" + prdt_name);
        tv_mob.setText("" + mobile);
        tv_address.setText("" + address);
        tvcus_name.setText("" + cus_name);
    }
}
