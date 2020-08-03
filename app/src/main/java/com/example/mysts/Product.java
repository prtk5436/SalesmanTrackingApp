package com.example.mysts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;


import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mysts.adapter.ProductAdapter;
import com.example.mysts.design.MBottomBar;
import com.example.mysts.sql.ProductModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Product extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener {
    FirebaseAuth auth;
    Context context;
    String TAG = "TAG";
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    TextView data;
    ProductAdapter pro_adapter;
    ListView lvlistview;

    MBottomBar mBottomBar;
    private boolean isOpenMenu2 = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        data = findViewById(R.id.tvDATA);
        auth = FirebaseAuth.getInstance();
        auth = FirebaseAuth.getInstance();  mBottomBar = new MBottomBar(findViewById(R.id.home_bottom_bar));
        setFabClick();
        setBarItemClick();


        Log.d(TAG, "onCreate:ShowCustomerList_Activity ");

        lvlistview = findViewById(R.id.lvlistviewProduct);
        Cursor cursor = new ProductModel(this).showProductDetails();
        pro_adapter = new ProductAdapter(context, cursor);
        lvlistview.setAdapter(pro_adapter);
        if (pro_adapter.isEmpty())
            data.setVisibility(View.VISIBLE);
        else {
            data.setVisibility(View.GONE);
        }
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        UpdateNavHeader();


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
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
                        Toast.makeText(Product.this, "Bar Favorite Click", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.bar_add_sale:
                        finish();
                        Intent i = new Intent(Product.this, SalesmanRegistrationActivity.class);
                        startActivity(i);
                        break;
                    case R.id.bar_add_prod:
                        finish();
                        Intent i2 = new Intent(Product.this, AddProductActivity.class);
                        startActivity(i2);break;
                    case R.id.bar_add_cust:
                        finish();
                        Intent i3 = new Intent(Product.this, CustomerRegistrationActivity.class);
                        startActivity(i3);break;
                    case R.id.bar_order:

                        finish();
                        Intent i4 = new Intent(Product.this, AddOrderActivity.class);
                        startActivity(i4);Toast.makeText(Product.this, "Bar Shopping Click", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.bar_more:
                        Toast.makeText(Product.this, "Bar More Click", Toast.LENGTH_LONG).show();
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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {

            case R.id.nav_salesman:
                finish();
                Intent i2 = new Intent(Product.this, Salesman.class);
                startActivity(i2);
                break;
            case R.id.nav_customer:
                finish();
                Intent i3 = new Intent(Product.this, Customer.class);
                startActivity(i3);
                break;
            case R.id.nav_order:
                finish();
                Intent i4 = new Intent(Product.this, Order.class);
                startActivity(i4);
                break;
            case R.id.nav_product:
                finish();
                Intent i5 = new Intent(Product.this, Product.class);
                startActivity(i5);
                break;

            case R.id.nav_logOut:
                auth.signOut();
                finish();
                Intent i6 = new Intent(Product.this, UserSelectionActivity.class);
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
