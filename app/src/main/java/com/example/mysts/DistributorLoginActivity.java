package com.example.mysts;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class DistributorLoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etemail, etpassword;
    TextView tv_newuser;
    Context context;
    ProgressBar progressBar;
    int i;
    Button btndistri, btnsales, btncontinue;
    private FirebaseAuth mAuth;
    LinearLayout l9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);


        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progbar);
        btndistri = (Button) findViewById(R.id.btn_distributor);
        btnsales = (Button) findViewById(R.id.btn_salesman);
        btncontinue = (Button) findViewById(R.id.btn_login);
        context = this;
        tv_newuser = findViewById(R.id.tv_newuser);
        etpassword = findViewById(R.id.etpassword);
        etemail = findViewById(R.id.etemail);
        l9 = findViewById(R.id.l9);
        tv_newuser.setOnClickListener(this);
        btndistri.setOnClickListener(this);
        btnsales.setOnClickListener(this);
        btncontinue.setOnClickListener(this);

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        if (mAuth.getCurrentUser() != null) {
            finish();
            updateUI();
        }
    }

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
                finish();
                Intent D = new Intent(context, DistributorLoginActivity.class);
                startActivity(D);
                break;
            case R.id.btn_salesman:
                if (i == 1) {
                    btndistri.setBackgroundResource(R.drawable.rect_outlinegrey);
                    btndistri.setTextColor(Color.parseColor("#aaaaaa"));
                }

                btnsales.setBackgroundResource(R.drawable.rect_outlineblue);
                btnsales.setTextColor(Color.parseColor("#009688"));
                i = 2;
                finish();
                Intent S = new Intent(context, SalesmanLoginActivity.class);
                startActivity(S);
                break;
            case R.id.tv_newuser:
                finish();
                tv_newuser.setVisibility(View.GONE);
                Intent i = new Intent(DistributorLoginActivity.this, AdminSignUpActivity.class);
                startActivity(i);
                break;

            case R.id.btn_login:
                logintoUser();

                break;
        }

    }

    private void logintoUser() {
        String email = etemail.getText().toString().trim();
        String password = etpassword.getText().toString().trim();
        if (email.isEmpty()) {
            etemail.setError("enter valid email");
            etemail.requestFocus();
            return;
        }
        if (password.isEmpty() || password.length() < 6) {
            etpassword.setError("enter at least 6 digit new password");
            etpassword.requestFocus();
            return;
        } else {
            btncontinue.setVisibility(View.GONE);
            l9.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("MSG", "signInWithEmail:success");
                                progressBar.setVisibility(View.GONE);
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(DistributorLoginActivity.this, "LOGIN SUCCESS..!!", Toast.LENGTH_SHORT).show();
                                updateUI();
                            } else {
                                // If sign in fails, display a message to the user.
                                progressBar.setVisibility(View.GONE);
                                btncontinue.setVisibility(View.VISIBLE);
                                l9.setVisibility(View.VISIBLE);
                                Log.w("MSG", "signInWithEmail:failure", task.getException());
                                Toast.makeText(DistributorLoginActivity.this, "Invalid Username or Password",
                                        Toast.LENGTH_LONG).show();
                                //                          updateUI(null);
                            }
                        }
                    });
        }
    }

    private void updateUI() {
        Intent salesman = new Intent(context, Salesman.class);
        startActivity(salesman);
    }
}
