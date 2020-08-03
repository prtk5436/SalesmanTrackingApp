package com.example.mysts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.example.mysts.adapter.SalesmanAdapter;
import com.example.mysts.design.MBottomBar;
import com.example.mysts.sql.DBHelper;
import com.example.mysts.sql.SalesmanModel;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Salesman extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    Context context;
    SalesmanAdapter sales_adapter;
    ListView lvlistview;
    String TAG = "TAG";
    TextView data;
    TextView newUser;
    ImageButton menu;
    EditText id;
    DBHelper db;

    MBottomBar mBottomBar;
    private boolean isOpenMenu2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salesman);/*
        menu = findViewById(R.id.menu);*/
        mBottomBar = new MBottomBar(findViewById(R.id.home_bottom_bar));
        setFabClick();
        setBarItemClick();
        db = new DBHelper(this);
        id = findViewById(R.id.et_id);
        data = findViewById(R.id.tvDATA);
     /*   FloatingActionButton fab = findViewById(R.id.fab_addSalesman);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Salesman.this, SalesmanRegistrationActivity.class);
                startActivity(i);
            }
        });*/

        newUser = findViewById(R.id.tv_newuser);
        Log.d(TAG, "onCreate:ShowSalesmanList_Activity ");
        lvlistview = findViewById(R.id.lvSalesmanlist);
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


    private void setFabClick() {
        mBottomBar.getFab().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isOpenMenu2 = !isOpenMenu2;
                changeBarMenu();
            }
        });
    }

    private void changeBarMenu() {
        if (isOpenMenu2) {
            mBottomBar.getBottomBar().setNavigationIcon(null);
            mBottomBar.getBottomBar().setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
            mBottomBar.getBottomBar().replaceMenu(R.menu.bottom_bar_menu_2);
            mBottomBar.fabAnimation(isOpenMenu2);
        } else {
            mBottomBar.getBottomBar().setNavigationIcon(R.drawable.ic_menu);
            mBottomBar.getBottomBar().setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
            mBottomBar.getBottomBar().replaceMenu(R.menu.bottom_bar_menu);
            mBottomBar.fabAnimation(isOpenMenu2);
        }
    }

    private void setBarItemClick() {
        mBottomBar.getBottomBar().setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.bar_favorite:
                        Toast.makeText(Salesman.this, "Bar Favorite Click", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.bar_add_sale:
                        finish();
                        Intent i = new Intent(Salesman.this, SalesmanRegistrationActivity.class);
                        startActivity(i);
                        break;
                    case R.id.bar_add_prod:
                        finish();
                        Intent i2 = new Intent(Salesman.this, AddProductActivity.class);
                        startActivity(i2);break;
                    case R.id.bar_add_cust:
                        finish();
                        Intent i3 = new Intent(Salesman.this, CustomerRegistrationActivity.class);
                        startActivity(i3);break;
                    case R.id.bar_order:

                        finish();
                        Intent i4 = new Intent(Salesman.this, AddOrderActivity.class);
                        startActivity(i4);Toast.makeText(Salesman.this, "Bar Shopping Click", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.bar_more:
                        Toast.makeText(Salesman.this, "Bar More Click", Toast.LENGTH_LONG).show();
                        break;
                }

                return false;
            }
        });/*
        final DrawerLayout navDrawer = findViewById(R.id.drawer_layout);*/
        NavigationView navigationView = findViewById(R.id.nav_view);
        mBottomBar.getBottomBar().setNavigationOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                final DrawerLayout navDrawer = findViewById(R.id.drawer_layout);
                if (!navDrawer.isDrawerOpen(GravityCompat.START))
                    navDrawer.openDrawer(Gravity.START);
                else navDrawer.closeDrawer(Gravity.END);
            }

        });
        navigationView.setNavigationItemSelectedListener(this);
        context = this;
    }

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
