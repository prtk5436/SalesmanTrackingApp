package com.example.mysts;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mysts.adapter.Salesman_Task_Adapter;
import com.example.mysts.sql.DBHelper;
import com.example.mysts.sql.MyOrderModel;

import java.util.List;

public class SalesmanPanelActivity extends AppCompatActivity {

    Context context;
    int sale_id;
    String TAG = "TAG",orderID;

    ListView lvtask_list;
    ImageButton btn_done1;
    Salesman_Task_Adapter salesman_task_adapter;
    Button btn_generateOTP;
    TextView tv_closed;
    EditText et_otp;
    Spinner spinner_select_orderID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String TAG = "TAG";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salesman_panel);
        context = this;

        spinner_select_orderID = (Spinner) findViewById(R.id.spinner_select_orderId);
        lvtask_list = findViewById(R.id.lvtask_list);
        context = this;
        btn_generateOTP = findViewById(R.id.btn_generateOTP);
        et_otp = findViewById(R.id.et_otp);
        TextView tvsale_id = findViewById(R.id.tv_sales_id);
        Log.d(TAG, "onCreate: Inside View Task Activity");


        add_orderID_ToSpinner();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sale_id = extras.getInt("sale_id");
        }

        Log.d(TAG, "onCreate: Inside Show Order Activity sale_id" + sale_id);
        String salesman_id = String.valueOf(sale_id);
        tvsale_id.setText(""+salesman_id);
        Cursor cursor = new MyOrderModel(this).getOrderDetailsOnId(sale_id);
        Log.d(TAG, "onCreate: Cursor to Adapter");
        salesman_task_adapter = new Salesman_Task_Adapter(context, cursor);
        lvtask_list.setAdapter(salesman_task_adapter);
        Log.d(TAG, "onCreate: Adapter to cusro");
    }
    private void add_orderID_ToSpinner() {

        DBHelper db = new DBHelper(getApplicationContext());
        List<String> lables = db.getAllSalesman();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lables);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_select_orderID.setAdapter(dataAdapter);
        click_orderID();
    }
    private void click_orderID() {
        spinner_select_orderID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // On selecting a spinner item
                orderID = parent.getItemAtPosition(position).toString();
                //  fetch_salesman_details(orderID);
                Log.d(TAG, "onItemSelected: salesman" + orderID);
                Toast.makeText(parent.getContext(), "You selected: " + orderID, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
}
