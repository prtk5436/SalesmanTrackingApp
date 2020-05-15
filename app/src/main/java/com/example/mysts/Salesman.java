package com.example.mysts;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mysts.adapter.SalesmanAdapter;
import com.example.mysts.sql.DBHelper;
import com.example.mysts.sql.SalesmanModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Salesman extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    Context context;
    SalesmanAdapter sales_adapter;
    ListView lvlistview;
    String TAG = "TAG";
    TextView data;
    TextView newUser;
    Button btn_delete;
    EditText id;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salesman);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        db = new DBHelper(this);
        id = findViewById(R.id.et_id);
        btn_delete = findViewById(R.id.btn_delete);
        data = findViewById(R.id.tvDATA);
        FloatingActionButton fab = findViewById(R.id.fab_addSalesman);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Salesman.this, SalesmanRegistrationActivity.class);
                startActivity(i);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        context = this;

        newUser = findViewById(R.id.tv_newuser);
        Log.d(TAG, "onCreate:ShowSalesmanList_Activity ");
        lvlistview = (ListView) findViewById(R.id.lvSalesmanlist);
        Cursor cursor = new SalesmanModel(this).showSalesmanDetails();
        sales_adapter = new SalesmanAdapter(context, cursor);
        lvlistview.setAdapter(sales_adapter);
        if (sales_adapter.isEmpty())
            data.setVisibility(View.VISIBLE);
        else {
            data.setVisibility(View.GONE);
        }
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        UpdateNavHeader();


        DeleteData();
    }

    private void DeleteData() {

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deleterows = db.DeleteSalesmanOnId(id.getText().toString());
                if (deleterows == 0) {
                    Toast.makeText(Salesman.this, "not Deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Salesman.this, " Deleted" + deleterows, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        if (mAuth.getCurrentUser() == null) {
            finish();
            Intent salesman = new Intent(Salesman.this, DistributorLoginActivity.class);
            startActivity(salesman);
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Toast.makeText(Salesman.this, "press Back again to exit ", Toast.LENGTH_SHORT).show();
            super.onBackPressed();

            finish();
            Intent salesman = new Intent(Salesman.this, UserSelectionActivity.class);
            startActivity(salesman);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            Intent i = new Intent(Salesman.this, Salesman.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {

            case R.id.nav_salesman:
                finish();
                Intent i2 = new Intent(Salesman.this, Salesman.class);
                startActivity(i2);
                break;
            case R.id.nav_customer:

                finish();
                Intent i3 = new Intent(Salesman.this, Customer.class);
                startActivity(i3);
                break;
            case R.id.nav_order:
                finish();
                Intent i4 = new Intent(Salesman.this, Order.class);
                startActivity(i4);
                break;

            case R.id.nav_product:
                finish();
                Intent i5 = new Intent(Salesman.this, Product.class);
                startActivity(i5);
                break;
            case R.id.nav_logOut:
                mAuth.signOut();
                finish();
                Intent i6 = new Intent(Salesman.this, UserSelectionActivity.class);
                startActivity(i6);
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void UpdateNavHeader() {
        NavigationView navigationView = findViewById(R.id.nav_view);

        View headerview = navigationView.getHeaderView(0);
        TextView navUserName = headerview.findViewById(R.id.nav_username);
        TextView navEmail = headerview.findViewById(R.id.nav_email);
        ImageView navProfileDP = headerview.findViewById(R.id.nav_iv_dp);

        navEmail.setText(currentUser.getEmail());
        navUserName.setText(currentUser.getDisplayName());

        Glide.with(this).load(currentUser.getPhotoUrl()).into(navProfileDP);
    }
}
