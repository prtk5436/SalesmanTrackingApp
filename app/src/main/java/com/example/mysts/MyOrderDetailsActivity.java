package com.example.mysts;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

public class MyOrderDetailsActivity extends AppCompatActivity {

    String sale_name = "", cust_name = "", cust_mob = "", product = "",price = "",location = "",time = "";
    int order_id;
    TextView tv_orderid,tvsale_name,tvcust_name,tvcust_mob,tv_loc,tv_time,tv_prdt,tv_price;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myorder_details);

        tv_orderid = (TextView) findViewById(R.id.tv_orderid);
        tvsale_name = (TextView) findViewById(R.id.tvsale_name);
        tvcust_name = (TextView) findViewById(R.id.tvcust_name);
        tvcust_mob = (TextView) findViewById(R.id.tvcust_mob);
        tv_loc = (TextView) findViewById(R.id.tv_loc);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_prdt = (TextView) findViewById(R.id.tvproduct);
        tv_price = (TextView) findViewById(R.id.tvprice);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            order_id = extras.getInt("orderId");
            sale_name = extras.getString("salesman_name");
            cust_name = extras.getString("customer_name");
            cust_mob = extras.getString("customer_mobile");
            product = extras.getString("product_name");
            price = extras.getString("product_price");
            location = extras.getString("order_location");
            time = extras.getString("order_time");
        }

        tv_orderid.setText("" + order_id);
        tvsale_name.setText("" + sale_name);
        tvcust_name.setText("" + cust_name);
        tvcust_mob.setText("" + cust_mob);
        tv_loc.setText("" + location);
        tv_time.setText("" + time);
        tv_prdt.setText("" + product);
        tv_price.setText("" + price);
    }
}


