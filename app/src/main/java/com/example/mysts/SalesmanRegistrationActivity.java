package com.example.mysts;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mysts.sql.SalesmanModel;

public class SalesmanRegistrationActivity  extends AppCompatActivity {

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
        if(name!=null || mob!=null || email!= null || password!=null)
        {
            long id = (long) model.addSalesman(name, email,password,mob,sale_cnt);
            if ( id != -1 ) {
                Toast.makeText(this, "Salesman Added with id : "+id, Toast.LENGTH_LONG).show();
                finish();

            } else
                {
                Toast.makeText(this, "Failed to add salesman.", Toast.LENGTH_LONG).show();
            }
        }

        else
        {
            Toast.makeText(this, "Do not Enter Empty Field", Toast.LENGTH_LONG).show();
        }
        et_email.setText("");
        et_mob.setText("");
        et_name.setText("");
        et_password.setText("");
    }
}
