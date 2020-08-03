package com.example.mysts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mysts.sql.SalesmanModel;

public class SalesmanRegistrationActivity extends AppCompatActivity {

    EditText et_name, et_mob, et_email, et_password;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salesman_registration);
        context = this;
        et_name = (EditText) findViewById(R.id.et_name);
        et_email = (EditText) findViewById(R.id.et_email);
        et_mob = (EditText) findViewById(R.id.et_mob);
        et_password = (EditText) findViewById(R.id.et_password);
    }

    //Add Salesman Details into database
    public void add_salesman(View view) {
        int sale_cnt = 0;
        String name = et_name.getText().toString();
        String mob = et_mob.getText().toString();
        String email = et_email.getText().toString();
        String password = et_password.getText().toString();
        SalesmanModel model = new SalesmanModel(this);
        if (!name.isEmpty() && name.length() >= 3  && !email.isEmpty() && email.contains("@") && email.contains(".") && !password.isEmpty()) {
            if (mob.length() == 10) {
                long id = (long) model.addSalesman(name, email, password, mob, sale_cnt);
                if (id != -1) {
                    Toast.makeText(this, "Salesman Added with id : " + id, Toast.LENGTH_SHORT).show();
                    finish();
                    Intent i = new Intent(SalesmanRegistrationActivity.this, Salesman.class);
                    startActivity(i);

                } else {
                    Toast.makeText(this, "Failed to add salesman.", Toast.LENGTH_LONG).show();
                }
            } else {
                et_mob.setError("enter 10 digit mobile no.");
            }
        } else {
            Toast.makeText(this, "please enter valid info", Toast.LENGTH_LONG).show();
        }
    }
}
