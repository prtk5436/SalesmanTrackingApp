package com.example.mysts;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mysts.sql.ProductModel;

public class AddProductActivity extends AppCompatActivity {

    EditText et_name, et_price;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        context = this;
        et_name = (EditText) findViewById(R.id.et_name);
        et_price = (EditText) findViewById(R.id.et_price);
    }

    //Add Salesman Details into database
    public void add_product(View view) {

        int prod_cnt = 0;
        String name = et_name.getText().toString();
        String price = et_price.getText().toString();
        ProductModel model = new ProductModel(this);
        if (name != null || price != null) {
            long id = (long) model.addProduct(name, price, prod_cnt);
            if (id != -1) {
                Toast.makeText(this, "New Product Added with id : " + id, Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to add New Product.", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Do not Enter Empty Field", Toast.LENGTH_LONG).show();
        }
        et_price.setText("");
        et_name.setText("");
    }
}

