package com.example.mysts;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mysts.sql.SalesmanModel;
import com.example.mysts.sql.tables.SalesmanTable;


public class SalesmanLoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etusername, etpassword;
    Button btn_submit_salesman;
    int i;
    Button btndistri, btnsales, btncontinue;
    Context context;
    String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salesman_login);
        etpassword = findViewById(R.id.etpassword);
        etusername = findViewById(R.id.etusername);

        btndistri = (Button) findViewById(R.id.btn_distributor);
        btnsales = (Button) findViewById(R.id.btn_salesman);
        btn_submit_salesman = findViewById(R.id.submit_salesman);
        context = this;
        btndistri.setOnClickListener(this);
        btnsales.setOnClickListener(this);
        btn_submit_salesman.setOnClickListener(this);

    }

    public void onClick(View v) {

        Drawable d = btndistri.getBackground();

        Drawable s = btnsales.getBackground();
        switch (v.getId()) {
            case R.id.btn_distributor:
                if (i == 2) {
                    btnsales.setBackgroundResource(R.drawable.rect_outlinegrey);
                    btnsales.setTextColor(Color.parseColor("#aaaaaa"));
                }
                btndistri.setBackgroundResource(R.drawable.rect_outlineblue);
                btndistri.setTextColor(Color.parseColor("#009688"));

                i = 1;
                finish();
                Intent D = new Intent(context, DistributorLoginActivity.class);
                startActivity(D);
                break;
            case R.id.btn_salesman:
                if (i == 1) {
                    btndistri.setBackgroundResource(R.drawable.rect_outlinegrey);
                    btndistri.setTextColor(Color.parseColor("#aaaaaa"));
                }

                btnsales.setBackgroundResource(R.drawable.rect_outlineblue);
                btnsales.setTextColor(Color.parseColor("#009688"));
                i = 2;
                finish();
                Intent S = new Intent(context, SalesmanLoginActivity.class);
                startActivity(S);
                break;

            case R.id.submit_salesman:
                Log.d(TAG, "onClick: inside method");
                submit_salesman();

        }

    }


    public void submit_salesman() {
        Log.d(TAG, "submit_salesman: inside method");
        String username = etusername.getText().toString();
        String password = etpassword.getText().toString();
        if (!username.isEmpty()) {
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
                    if (sale_password.equals(password) && !sale_password.isEmpty()) {
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

        } else {
            etusername.setError("enter salesman id");
        }
    }
}
