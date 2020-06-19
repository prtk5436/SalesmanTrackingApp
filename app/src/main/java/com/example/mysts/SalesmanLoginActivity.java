package com.example.mysts;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mysts.sql.SalesmanModel;
import com.example.mysts.sql.tables.SalesmanTable;


public class SalesmanLoginActivity extends AppCompatActivity {
    EditText etusername, etpassword;
    Button btn_submit_salesman;
    Context context;
    String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salesman_login);
        etpassword = findViewById(R.id.etpassword);
        etusername = findViewById(R.id.etusername);
        btn_submit_salesman = findViewById(R.id.submit_salesman);
        context = this;
        btn_submit_salesman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: inside method");
                submit_salesman();


            }
        });
    }

    public void submit_salesman() {
        Log.d(TAG, "submit_salesman: inside method");
        String username = etusername.getText().toString();
        String password = etpassword.getText().toString();
        int sale_id = Integer.parseInt(username);
        Log.d(TAG, "submit_salesman: sale_id" + sale_id);
        Log.d(TAG, "submit_salesman: password" + password);

        SalesmanModel salesmanModel = new SalesmanModel(context);
        Cursor cursor3 = salesmanModel.getSaleDetailsOnId(sale_id);
        if (cursor3.moveToFirst()) {
            do {
                String sale_password = cursor3.getString(cursor3.getColumnIndex(SalesmanTable.Columns.PASSWORD));
                // int id= cursor3.getInt(cursor3.getColumnIndex(SalesmanTable.Columns.SALE_ID));
                Log.d(TAG, "SalesmanModel: salesman_name" + sale_password);
                if (sale_password.equals(password)) {
                    Log.d(TAG, "submit_salesman: inside if loop");
                    finish();
                    Intent intent = new Intent(context, SalesmanPanelActivity.class);
                    Toast.makeText(SalesmanLoginActivity.this, "successfuly Login..!", Toast.LENGTH_SHORT).show();
                    intent.putExtra("sale_id", sale_id);
                    startActivity(intent);
                } else {
                    Toast.makeText(context, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                }
            } while (cursor3.moveToNext());
        }

    }
}
