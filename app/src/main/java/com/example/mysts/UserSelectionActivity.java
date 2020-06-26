package com.example.mysts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class UserSelectionActivity extends AppCompatActivity implements View.OnClickListener {

    Context context;
    Button btndistri, btnsales, btncontinue;
    String TAG = "TAG";
    TextView tv_newuser;
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_selection);
        context = this;
        tv_newuser = findViewById(R.id.tv_newuser);

        btndistri = (Button) findViewById(R.id.btn_distributor);
        btnsales = (Button) findViewById(R.id.btn_salesman);
        btncontinue = (Button) findViewById(R.id.btn_continue);

        btndistri.setOnClickListener(this);
        btnsales.setOnClickListener(this);
        btncontinue.setOnClickListener(this);
    }

    @SuppressLint("ResourceAsColor")
    @Override
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
                /*Intent D = new Intent(context, DistributorLoginActivity.class);
                startActivity(D);
                */
                break;
            case R.id.btn_salesman:
                if (i == 1) {
                    btndistri.setBackgroundResource(R.drawable.rect_outlinegrey);
                    btndistri.setTextColor(Color.parseColor("#aaaaaa"));
                }

                btnsales.setBackgroundResource(R.drawable.rect_outlineblue);
                btnsales.setTextColor(Color.parseColor("#009688"));
                i = 2;
                /*       Intent S = new Intent(context, SalesmanLoginActivity.class);
                startActivity(S);
*/
                break;

            case R.id.btn_continue:
                login();
        }

    }

    private void login() {
        if (i == 1) {
            Log.d(TAG, "onClick: DistributorLoginActivity");
            Intent D = new Intent(context, DistributorLoginActivity.class);
            startActivity(D);
        } else if (i == 2) {

            Log.d(TAG, "onClick: SalesmanLoginActivity");
            Intent S = new Intent(context, SalesmanLoginActivity.class);
            startActivity(S);
        } else {
            Toast.makeText(context, "select role", Toast.LENGTH_SHORT).show();
        }
    }
}
