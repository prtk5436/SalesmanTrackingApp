package com.example.mysts;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mysts.sql.CustomerModel;

public class CustomerRegistrationActivity extends AppCompatActivity {
    EditText et_name, et_mob, et_address;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);
        context = this;
        et_name = (EditText) findViewById(R.id.et_name);
        et_address = (EditText) findViewById(R.id.et_address);
        et_mob = (EditText) findViewById(R.id.et_mob);
    }

    //Add Salesman Details into database
    public void add_customer(View view) {

        int cust_cnt = 0;
        String name = et_name.getText().toString();
        String mob = et_mob.getText().toString();
        String address = et_address.getText().toString();
        CustomerModel model = new CustomerModel(this);
        if (name != null || mob != null || address != null) {
            long id = (long) model.addCustomer(name, address, mob, cust_cnt);
            if (id != -1) {
                Toast.makeText(this, "New Customer Added with id : " + id, Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to add salesman.", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Do not Enter Empty Field", Toast.LENGTH_LONG).show();
        }
        et_address.setText("");
        et_mob.setText("");
        et_name.setText("");
    }
}

