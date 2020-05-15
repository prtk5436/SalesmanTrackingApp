package com.example.mysts;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.mysts.adapter.Salesman_Task_Adapter;
import com.example.mysts.sql.OrderModel;

import static android.content.ContentValues.TAG;

public class ViewTaskActivity  extends AppCompatActivity{
    ListView lvtask_list;
    Context context;
    int sale_id;
    Salesman_Task_Adapter salesman_task_adapter;
    Button btn_done1, btn_generateOTP;
    EditText et_otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);
        lvtask_list = findViewById(R.id.lvtask_list);
        context = this;
        btn_generateOTP = findViewById(R.id.btn_generateOTP);
        et_otp = findViewById(R.id.et_otp);

        btn_done1 = findViewById(R.id.btn_done1);
        Log.d(TAG, "onCreate: Inside View Task Activity");


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sale_id = extras.getInt("sale_id");
        }

        Log.d(TAG, "onCreate: Inside Show Order Activity sale_id" + sale_id);
        Cursor cursor = new OrderModel(this).getAllOrder();
        Log.d(TAG, "onCreate: Cursor to Adapter");
        salesman_task_adapter = new Salesman_Task_Adapter(context, cursor);
        lvtask_list.setAdapter(salesman_task_adapter);
        Log.d(TAG, "onCreate: Adapter to cusro");
    }
}
