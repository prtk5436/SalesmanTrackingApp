package com.example.mysts;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SalesmanDetailsActivity extends AppCompatActivity {

    String sale_name = "", mobile = "", email = "", password = "";
    int sale_id;
    TextView tvsale_name, tv_mob, tv_email, tv_sale_id, tv_password;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salesman_details);
        tvsale_name = (TextView) findViewById(R.id.tvsale_name);
        tv_email = (TextView) findViewById(R.id.tv_email);
        tv_mob = (TextView) findViewById(R.id.tv_mob);
        tv_sale_id = (TextView) findViewById(R.id.tv_sales_id);
        tv_password = (TextView) findViewById(R.id.tvpassword);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sale_name = extras.getString("sale_name");
            email = extras.getString("email");
            mobile = extras.getString("mobile");
            sale_id = extras.getInt("sale_id");
            password = extras.getString("password");
        }

        tvsale_name.setText("" + sale_name);
        tv_email.setText("" + email);
        tv_sale_id.setText("" + sale_id);
        tv_mob.setText("" + mobile);
        tv_password.setText("" + password);
    }
}

