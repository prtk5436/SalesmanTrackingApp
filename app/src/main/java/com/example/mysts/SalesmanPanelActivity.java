package com.example.mysts;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mysts.adapter.Salesman_Task_Adapter;
import com.example.mysts.sql.OrderModel;

public class SalesmanPanelActivity  extends AppCompatActivity{

    Context context;
    int sale_id;
    String TAG="TAG";

    ListView lvtask_list;

    Salesman_Task_Adapter salesman_task_adapter;
    Button btn_done1,btn_generateOTP;
    TextView tv_closed;
    EditText et_otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String TAG="TAG";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salesman_panel);
        context=this;

        lvtask_list= findViewById(R.id.lvtask_list);
        context=this;
        btn_generateOTP = findViewById(R.id.btn_generateOTP);
        et_otp=findViewById(R.id.et_otp);

        btn_done1=findViewById(R.id.btn_done1);
        Log.d(TAG, "onCreate: Inside View Task Activity");


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sale_id = extras.getInt("sale_id");
        }

        Log.d(TAG, "onCreate: Inside Show Order Activity sale_id"+sale_id);
        Cursor cursor = new OrderModel(this).getAllOrder();
        Log.d(TAG, "onCreate: Cursor to Adapter");
        salesman_task_adapter= new Salesman_Task_Adapter(context,cursor);
        lvtask_list.setAdapter(salesman_task_adapter);
        Log.d(TAG, "onCreate: Adapter to cusro");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.salesman_logout, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            finish();
            Intent i = new Intent(SalesmanPanelActivity.this, UserSelectionActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /* public void view_task(View view) {
        Log.d(TAG, "ViewTaskActivity intent called view_task: ");
        Intent intent= new Intent(context, ViewTaskActivity.class);
       intent.putExtra("sale_id",sale_id);
        startActivity(intent);

    }
*/
}
