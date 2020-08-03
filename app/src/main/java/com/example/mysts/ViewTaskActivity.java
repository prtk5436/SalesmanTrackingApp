package com.example.mysts;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mysts.adapter.OrderAdapter;
import com.example.mysts.sql.MyOrderModel;

import static android.content.ContentValues.TAG;

public class ViewTaskActivity extends AppCompatActivity {
    ListView lvtask_list;
    Context context;
    int sale_id;
    OrderAdapter salesman_task_adapter;
    Button btn_done1, btn_generateOTP;
    EditText et_otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);
        lvtask_list = findViewById(R.id.lvtask_list);
        context = this;

        Log.d(TAG, "onCreate: Inside View Task Activity");


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sale_id = extras.getInt("sale_id");
        }

        Log.d(TAG, "onCreate: Inside Show Order Activity sale_id" + sale_id);
        Cursor cursor = new MyOrderModel(this).showOrderDetails();
        Log.d(TAG, "onCreate: Cursor to Adapter");
        salesman_task_adapter = new OrderAdapter(context, cursor);
        lvtask_list.setAdapter(salesman_task_adapter);
        Log.d(TAG, "onCreate: Adapter to cusro");
    }
}
