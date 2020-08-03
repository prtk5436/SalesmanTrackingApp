package com.example.mysts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mysts.sql.DBHelper;
import com.example.mysts.sql.OtpModel;
import com.example.mysts.sql.SalesmanModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTPGenerationActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvorderId, et_mobno, et_cust_name;
    EditText et_otp;
    Context context;
    String TAG = "TAG", cus_name = "", mobile = "";
    int otp, cus_id, orderno = 0;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallback;
    String verification_code;

    ProgressBar progressBar;
    FirebaseAuth mAuth;
    Button btn_done, btn_generateOTP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpgeneration);
        context = this;

        Log.d(TAG, "onCreate: inside otp generation");
        progressBar = findViewById(R.id.progbar);
        tvorderId = findViewById(R.id.tvOrderno);
        et_cust_name = (TextView) findViewById(R.id.et_cust_name);
        et_mobno = (TextView) findViewById(R.id.et_mobno);
        et_otp = findViewById(R.id.et_otp);
        btn_done = findViewById(R.id.btn_done);
        btn_generateOTP = findViewById(R.id.btn_generateOTP);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            orderno = extras.getInt("orderId");
            cus_name = extras.getString("customer_name");
            mobile = extras.getString("customer_mobile");
        }
        tvorderId.setText("Order no." + orderno);
        et_mobno.setText("" + mobile);
        et_cust_name.setText("Customer Name : " + cus_name);
        btn_generateOTP.setOnClickListener(this);
        btn_done.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();


        mcallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Log.d(TAG, "onVerificationCompleted:" + phoneAuthCredential);
                signInPhone(phoneAuthCredential);
                Toast.makeText(context, "VerificationCompleted", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.w(TAG, "onVerificationFailed", e);

            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                //super.onCodeSent(s, forceResendingToken);
                Log.d(TAG, "onCodeSent:" + s);
                verification_code = s;
                Toast.makeText(context, "OTP sent on customers mob. no.",Toast.LENGTH_SHORT).show();
            }
        };
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_generateOTP:
                progressBar.setVisibility(View.VISIBLE);
                generate_otp();
                break;
            case R.id.btn_done:
                doneOrder();
                break;
        }
    }


    public void generate_otp() {
        Log.d(TAG, "send_sms: inside");
        String number = et_mobno.getText().toString();
        Log.d(TAG, "send_sms: " + number);
        PhoneAuthProvider.getInstance().verifyPhoneNumber
                ("+91" + number, 60, TimeUnit.SECONDS, this, mcallback);
        Log.d(TAG, "number" + number);
        progressBar.setVisibility(View.GONE);
        et_otp.setVisibility(View.VISIBLE);
        btn_done.setVisibility(View.VISIBLE);
    }

    public void signInPhone(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            finish();
                            Intent I = new Intent(OTPGenerationActivity.this, SalesmanPanelActivity.class);
                            startActivity(I);
                            Toast.makeText(context, "Task Completed..!!", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    public void doneOrder() {

        OtpModel otpModel = new OtpModel(context);
        Log.d(TAG, "verify: Inside Verify");
        String input_code = et_otp.getText().toString();

        verifyphonenumber(verification_code, input_code);

        // otp = Integer.parseInt(et_otp.getText().toString());

        long id = otpModel.addOtpDetails(cus_id, input_code);
        if (id != -1) {

            DeleteData();
            Toast.makeText(this, "Otp Added with id : " + id, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Failed to add Otp.", Toast.LENGTH_LONG).show();
        }


        int sale_id = 1;
        SalesmanModel salesmanModel = new SalesmanModel(context);
        int sale_cnt = salesmanModel.incrementSaleCnt(sale_id);
        Log.d(TAG, "order_done: sale_cnt :" + sale_cnt);

    }

    private void verifyphonenumber(String verification_code, String input_code) {
        Log.d(TAG, "verifyphonenumber: inside ");
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verification_code, input_code);
        signInPhone(credential);
    }

    private void DeleteData() {
        DBHelper db = new DBHelper(OTPGenerationActivity.this);
        String S_order_id = String.valueOf(orderno);
        Integer deleterows = db.DeleteOrderOnId(S_order_id);
        if (deleterows > 0) {
            Toast.makeText(OTPGenerationActivity.this, " Deleted" + deleterows, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(OTPGenerationActivity.this, "not Deleted", Toast.LENGTH_SHORT).show();
        }

    }
}
