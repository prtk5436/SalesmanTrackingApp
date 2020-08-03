package com.example.mysts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mysts.sql.CustomerModel;

public class CustomerRegistrationActivity extends AppCompatActivity {
    EditText et_name, et_mob, et_address;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);
        context = this;
        et_name = findViewById(R.id.et_name);
        et_address = findViewById(R.id.et_address);
        et_mob = findViewById(R.id.et_mob);
    }

    //Add Salesman Details into database
    public void add_customer(View view) {

        int cust_cnt = 0;
        String name = et_name.getText().toString();
        String mob = et_mob.getText().toString();
        String address = et_address.getText().toString();
        CustomerModel model = new CustomerModel(this);
        if (!name.isEmpty() && name.length() >= 3 && !address.isEmpty() && address.length() >= 3) {
            if (mob.length() == 10) {
                long id = model.addCustomer(name, address, mob, cust_cnt);
                if (id != -1) {
                    Toast.makeText(this, "New Customer Added with id : " + id, Toast.LENGTH_LONG).show();
                    finish();
                    Intent i = new Intent(CustomerRegistrationActivity.this, Customer.class);
                    startActivity(i);
                } else {
                    Toast.makeText(this, "Failed to add New Customer.", Toast.LENGTH_LONG).show();
                }
            } else {
                et_mob.setError("enter 10 digit mobile no.");
            }
        } else {
            Toast.makeText(this, "Do not Enter Empty Field", Toast.LENGTH_LONG).show();
        }
    }
}
