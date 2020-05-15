/*
package mayuri.softcrowd.com.example.salesmantrackingapp;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mayuri.softcrowd.com.example.salesmantrackingapp.sql.CustomerModel;
import mayuri.softcrowd.com.example.salesmantrackingapp.sql.DBHelper;
import mayuri.softcrowd.com.example.salesmantrackingapp.sql.OrderModel;
import mayuri.softcrowd.com.example.salesmantrackingapp.sql.ProductModel;
import mayuri.softcrowd.com.example.salesmantrackingapp.sql.SalesmanModel;
import mayuri.softcrowd.com.example.salesmantrackingapp.sql.tables.CustomerTable;
import mayuri.softcrowd.com.example.salesmantrackingapp.sql.tables.ProductTable;
import mayuri.softcrowd.com.example.salesmantrackingapp.sql.tables.SalesmanTable;


public class AddOrderActivity extends AppCompatActivity {
    private static final String TAG ="list items: " ;
    Spinner spinner_select_time,spinner_select_salesman,spinner_select_product,spinner_select_customer ;
    TextView  tvproduct,tvprice,  tvaddress, tvmob;
    EditText ettime;
    Context context;
    String  cus_name,prdt_name,sale_name,address,time_am_pm,time,mobile;
    static String label;
    int cus_id, prdt_id, sale_id, price, current_pos=0;
    Cursor cursor;
 ArrayList<String> cus_name_list= new ArrayList<>();
    ArrayList<String> product_name_list= new ArrayList<>();
    ArrayList<String> salesman_list= new ArrayList<>();
    ArrayAdapter<String> dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);
        context=this;

        Log.d(TAG, "AddOrderActivity: in activity ");
        spinner_select_customer=(Spinner) findViewById(R.id.spinner_select_customer);
        spinner_select_product=(Spinner) findViewById(R.id.spinner_select_product);
        spinner_select_salesman=(Spinner) findViewById(R.id.spinner_select_salesman);
        spinner_select_time=(Spinner) findViewById(R.id.spinner_select_time);
        tvprice =(TextView) findViewById(R.id.tvprice);
        tvmob =(TextView) findViewById(R.id.tvmob);
        tvaddress =(TextView) findViewById(R.id.tvaddress);
        tvproduct=(TextView) findViewById(R.id.tvproduct);
        ettime=(EditText) findViewById(R.id.ettime);


        add_cusname_ToSpinner();

        add_productname_ToSpinner();
        add_salename_ToSpinner();
       //  getTime();

    }

    private void getTime() {
   spinner_select_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                time_am_pm = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        time= ettime.getText().toString()+" "+time_am_pm;
    }
    private void add_salename_ToSpinner() {
        // database handler
        SalesmanModel db = new SalesmanModel(getApplicationContext());
        // Spinner Drop down elements
        List<String> lables = db.getAllSalesman();
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);
        // Drop down layout style - list view with radio button
      //  dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner_select_salesman.setAdapter(dataAdapter);
   spinner_select_salesman.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // On selecting a spinner item
                String label = parent.getItemAtPosition(position).toString();
                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "You selected: " + label, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void add_cusname_ToSpinner(){
        // database handler
        CustomerModel db = new CustomerModel(getApplicationContext());
        // Spinner Drop down elements
        List<String> lables = db.getAllLabels();
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);
        // Drop down layout style - list view with radio button
       // dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner_select_customer.setAdapter(dataAdapter);
  spinner_select_customer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // On selecting a spinner item
                  String label = parent.getItemAtPosition(position).toString();

  cursor = new CustomerModel(AddOrderActivity.this).getCustomerDetails(label);
                    try {
                        while (cursor.moveToNext()) {
                            address=cursor.getString(cursor.getColumnIndex(CustomerTable.Columns.ADDRESS));

                            mobile= cursor.getString(cursor.getColumnIndex(CustomerTable.Columns.MOBILE));;
                            Log.d(TAG, "onItemSelected: address"+address);
                            Log.d(TAG, "onItemSelected: mobile"+mobile);
                        }
                        tvaddress.setText(""+address);
                    tvmob.setText(""+mobile);
                } finally {
                    cursor.close();
                }
                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "You selected: " + label, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                     }
        });

    }
  private void add_cusname() {
       // spinner_select_customer.setAdapter(null);
        Log.d(TAG, "Customer Spinner ");
        Cursor cursor1 = new CustomerModel(this).getAllCustomer();
           if(cursor1!= null) {
                Log.d(TAG, "cursor is not null ");
                while (cursor1.moveToNext()) {
                    Log.d(TAG, "getAllCustomer from model: " +  cursor1.getString(1));
                    cus_name_list.add(String.valueOf(cursor1.getString(1)));
                }
            }else
                Log.d(TAG, "Cursor is empty");
        Log.d(TAG, "Customer Spinner Items: ");
        for(int i=0;i<cus_name_list.size();i++)
            Log.d(TAG, "add_cusname_ToSpinner: "+cus_name_list.get(i));
       dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cus_name_list);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner_select_customer.setAdapter(dataAdapter);

///*****************
      spinner_select_customer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              String cus_name = parent.getItemAtPosition(position).toString();

              cursor = new CustomerModel(AddOrderActivity.this).getCustomerDetails(cus_name);

              try {
                  while (cursor.moveToNext()) {
                      address= String.valueOf(cursor.getColumnIndex("address"));
                      mobile= cursor.getColumnIndex("mobile");
                  }
              } finally {
                  cursor.close();
              }
              //cus_name_list.removeAll(cus_name_list);

          }
          @Override
          public void onNothingSelected(AdapterView<?> parent) {
              cus_name_list.clear();
              dataAdapter.clear();
              dataAdapter.notifyDataSetChanged();
          }
      });
       // dataAdapter.clear();
       // dataAdapter.notifyDataSetChanged();

        tvmob.setText(""+mobile);
        tvaddress.setText(address);
    }


    private void add_productname_ToSpinner() {
        // database handler
        final ProductModel model = new ProductModel(getApplicationContext());
        // Spinner Drop down elements
        final List<String> lables = model.getAllProduct();
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, lables);

        // Drop down layout style - list view with radio button

       // dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner_select_product.setAdapter(dataAdapter);

     //   int selectedPos = spinner_select_product.getSelectedItemPosition();
       click_product();
        //fetchdetails();

    }

    private void click_product()
    {
        spinner_select_product.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // On selecting a spinner item
                label = parent.getItemAtPosition(position).toString();

                // int  price = new ProductModel(AddOrderActivity.this).getProductDetails(label);
                // int price = model.getProductDetails(label);
                // fetchdetails(label);
                // tvprice.setText(""+price);


  else
                {
                    TextView spinner_item_text = (TextView) view;
                    //write your code here
                }



                Log.d(TAG, "onItemSelected: "+label);
                tvproduct.setText(""+label);
              // fetchdetails(label);
                // Showing selected spinner item
                Toast.makeText(parent.getContext(), "You selected: " + label, Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void fetchdetails(String label)
    {
        String item= tvproduct.getText().toString();
        Log.d(TAG, "fetchdetails: item "+item);
        String pricek;
        Log.d(TAG, "fetchdetails: label"+label);
        ProductModel model= new ProductModel(AddOrderActivity.this);
          pricek = model.getDetails(label);
        Log.d(TAG, "fetchdetails:price "+pricek);

          tvprice.setText(""+pricek);

    }
    public void submit_order(View view) {
        cursor = new CustomerModel(this).getCustomerId(cus_name);
        try {
            while (cursor.moveToNext()) {
                cus_id= cursor.getColumnIndex("mobile");
            }
        } finally {
            cursor.close();
        }
        cursor = new ProductModel(this).getProductId(prdt_name);
        try {
            while (cursor.moveToNext()) {
                prdt_id= cursor.getColumnIndex("mobile");
            }
        } finally {
            cursor.close();
        }
        cursor = new SalesmanModel(this).getSalesmanId(prdt_name);
        try {
            while (cursor.moveToNext()) {
                sale_id= cursor.getColumnIndex("salesmanId");
            }
        } finally {
            cursor.close();
        }
        OrderModel model = new OrderModel(this);
        //addOrder(int cus_id, int sale_id, int prdt_id, int time)
        long id = (long) model.addOrder(cus_id, sale_id, prdt_id,time);
        if ( id != -1 ) {
            Toast.makeText(this, "Order Added with id : "+id, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Failed to add Order.", Toast.LENGTH_LONG).show();
        }
        spinner_select_time.setPrompt("Select Time");
        spinner_select_salesman.setPrompt("Select Salesman");
        spinner_select_product.setPrompt("Select Product");
        spinner_select_customer.setPrompt("Select Customer");
        ettime.setText("");
        tvaddress.setText("");
        tvmob.setText("");
        tvprice.setText("");
    }
}
*/

package com.example.mysts;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysts.sql.CustomerModel;
import com.example.mysts.sql.DBHelper;
import com.example.mysts.sql.OrderModel;
import com.example.mysts.sql.ProductModel;
import com.example.mysts.sql.SalesmanModel;
import com.example.mysts.sql.tables.CustomerTable;
import com.example.mysts.sql.tables.ProductTable;
import com.example.mysts.sql.tables.SalesmanTable;

import java.util.List;


public class AddOrderActivity  extends AppCompatActivity {
    private static final String TAG = "list items: ";
    Spinner spinner_select_time, spinner_select_salesman, spinner_select_product, spinner_select_customer;
    TextView tvproduct, tvprice, tvaddress, tvmob;
    EditText ettime, ettime_value;
    Context context;
    String product, salesman, customer, time, time_am_pm;
    Cursor order_cursor;
    int cus_id, prdt_id, sale_id, price, current_pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_order);

        Log.d(TAG, "AddOrderActivity: in activity ");
        spinner_select_customer = (Spinner) findViewById(R.id.spinner_select_customer);
        spinner_select_product = (Spinner) findViewById(R.id.spinner_select_product);
        spinner_select_salesman = (Spinner) findViewById(R.id.spinner_select_salesman);
        spinner_select_time = (Spinner) findViewById(R.id.spinner_select_time);
        tvprice = (TextView) findViewById(R.id.tvprice);
        tvmob = (TextView) findViewById(R.id.tvmob);
        tvaddress = (TextView) findViewById(R.id.tvaddress);
        tvproduct = (TextView) findViewById(R.id.tvproduct);
        ettime = (EditText) findViewById(R.id.ettime);
        //  ettime_value = (EditText) findViewById(R.id.ettime_value);


        add_cusname_ToSpinner();
        add_productname_ToSpinner();
        add_salename_ToSpinner();
        getTime();
    }

    private void add_cusname_ToSpinner() {

        DBHelper db = new DBHelper(getApplicationContext());
        List<String> lables = db.getAllLabels();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lables);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_select_customer.setAdapter(dataAdapter);
        click_customer();
    }

    private void click_customer() {
        spinner_select_customer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // On selecting a spinner item
                customer = parent.getItemAtPosition(position).toString();
                Log.d(TAG, "onItemSelected: customer" + customer);
                fetch_customer_details(customer);
                Toast.makeText(parent.getContext(), "You selected: " + customer, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    private void fetch_customer_details(String label) {

        Log.d(TAG, "fetchdetails: item " + label);
        String address="",mobile="";
        Cursor customer_cursor = new CustomerModel(AddOrderActivity.this).getCustomerDetails(label);
        try {
            if(customer_cursor.moveToFirst())
                do {
                    Log.d(TAG, "fetch_customer_details: inside if loop");
                    address = customer_cursor.getString(customer_cursor.getColumnIndex(CustomerTable.Columns.ADDRESS));
                    mobile = customer_cursor.getString(customer_cursor.getColumnIndex(CustomerTable.Columns.MOBILE));
                    cus_id= customer_cursor.getInt(customer_cursor.getColumnIndex(CustomerTable.Columns.CUST_ID));
                    Log.d(TAG, "onItemSelected: address" + address);
                    Log.d(TAG, "onItemSelected: mobile" + mobile);
                    Log.d(TAG, "fetch_customer_details: id "+ cus_id);
                } while (customer_cursor.moveToNext());
            tvaddress.setText("" + address);
            tvmob.setText("" + mobile);
        } finally {
            customer_cursor.close();
        }
    }

    private void add_productname_ToSpinner() {

         DBHelper db = new DBHelper(getApplicationContext());
         List<String> lables = db.getAllProduct();
         ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lables);
         dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         spinner_select_product.setAdapter(dataAdapter);
         click_product();

     }
     private void click_product()
     {
         spinner_select_product.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                 product = parent.getItemAtPosition(position).toString();
                 Log.d(TAG, "onItemSelected: product"+product);
                 fetch_product_details(product);
                 Toast.makeText(parent.getContext(), "You selected: " + product, Toast.LENGTH_LONG).show();
             }
             @Override
             public void onNothingSelected(AdapterView<?> parent) {
             }
         });
     }
    private void fetch_product_details(String label) {

        Log.d(TAG, "fetch_product_details: item " + label);
        int  price =0;
        Cursor product_cursor = new ProductModel(AddOrderActivity.this).getProductDetails(label);
        try {
            if(product_cursor.moveToFirst())
                do {
                    Log.d(TAG, "fetch_product_details: inside if loop");
                    price = product_cursor.getInt(product_cursor.getColumnIndex(ProductTable.Columns.PRICE));
                    Log.d(TAG, "fetch_product_details: price" + price);
                    prdt_id= product_cursor.getInt(product_cursor.getColumnIndex(ProductTable.Columns.PROD_ID));
                    Log.d(TAG, "fetch_product_details: prdt_id"+prdt_id);
                } while (product_cursor.moveToNext());
            tvprice.setText("" + price);
        } finally {
            product_cursor.close();
        }
    }



    private void add_salename_ToSpinner() {

        DBHelper db = new DBHelper(getApplicationContext());
        List<String> lables = db.getAllSalesman();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lables);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_select_salesman.setAdapter(dataAdapter);
        click_salesman();
    }

    private void click_salesman() {
        spinner_select_salesman.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // On selecting a spinner item
                salesman = parent.getItemAtPosition(position).toString();


                Log.d(TAG, "onItemSelected: salesman" + salesman);
Toast.makeText(parent.getContext(), "You selected: " + salesman, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    private void getTime() {
        spinner_select_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // time_am_pm = parent.getItemAtPosition(position).toString();
                time_am_pm = parent.getSelectedItem().toString();
                Log.d(TAG, "onItemSelected: time_am_pm" + time_am_pm);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        String timer1 = ettime.getText().toString();
        Log.d(TAG, "submit_order: timer1" + timer1);
        time = timer1 + " " + time_am_pm;
        Log.d(TAG, "getTime: time" + time);
    }

    public void submit_order(View view) {

        order_cursor = new SalesmanModel(this).getSalesmanId(salesman);
        try {
            if(order_cursor.moveToFirst())
                do {
                    sale_id= order_cursor.getInt(order_cursor.getColumnIndex(SalesmanTable.Columns.SALE_ID));
                    Log.d(TAG, "order_cursor: sale_id"+sale_id);
                } while (order_cursor.moveToNext());
            tvprice.setText("" + price);
        } finally {
            order_cursor.close();
        }


        String t= ettime.getText().toString();
        Log.d(TAG, "submit_order: t : "+t);

        String finalT= t+" "+time_am_pm;
        Log.d(TAG, "submit_order: finalT :"+ finalT);
        Log.d(TAG, "submit_order: time_am_pm"+time_am_pm);

        OrderModel model = new OrderModel(this);
        Log.d(TAG, "submit_order: cus_id"+cus_id);
        Log.d(TAG, "submit_order: sale_id"+sale_id);
        Log.d(TAG, "submit_order: prdt_id "+prdt_id);
        Log.d(TAG, "submit_order: time "+ finalT);

        int order_cnt=0;
        if(cus_id!=0 && sale_id!=0 && prdt_id!=0 && finalT!=null) {
            long id = (long) model.addOrder(cus_id, sale_id, prdt_id, finalT, order_cnt);
            Log.d(TAG, "submit_order: Id  :"+ id);
            if (id != -1) {
                Toast.makeText(this, "Order Added with id : " + id, Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(this, "Failed to add Order.", Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            Toast.makeText(context,"Do not Enter Empty Field",Toast.LENGTH_SHORT).show();
        }
        spinner_select_time.setPrompt("Select Time");
        spinner_select_salesman.setPrompt("Select Salesman");
        spinner_select_product.setPrompt("Select Product");
        spinner_select_customer.setPrompt("Select Customer");
        ettime.setText("");
        tvaddress.setText("");
        tvmob.setText("");
        tvprice.setText("");
    }
}


