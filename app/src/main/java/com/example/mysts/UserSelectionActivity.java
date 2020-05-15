package com.example.mysts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class UserSelectionActivity   extends AppCompatActivity implements View.OnClickListener {

    Context context;
    Button btndistri, btnsales;
    String TAG = "TAG";
    TextView tv_newuser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_selection);
        context = this;
        tv_newuser=findViewById(R.id.tv_newuser);

        btndistri = (Button) findViewById(R.id.btn_distributor);
        btnsales = (Button) findViewById(R.id.btn_salesman);

        btndistri.setOnClickListener(this);
        btnsales.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_distributor:
                Log.d(TAG, "onClick: DistributorLoginActivity");
                finish();

                Intent D = new Intent(context, DistributorLoginActivity.class);
                startActivity(D);
                break;
            case R.id.btn_salesman:
                Log.d(TAG, "onClick: SalesmanLoginActivity");
                Intent S = new Intent(context, SalesmanLoginActivity.class);
                startActivity(S);
                break;
        }

    }
}
