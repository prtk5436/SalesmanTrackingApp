package com.example.mysts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class DistributorLoginActivity extends AppCompatActivity {

    EditText etemail, etpassword;
    TextView tv_newuser;
    Context context;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);


        mAuth = FirebaseAuth.getInstance();
        context = this;
        tv_newuser = findViewById(R.id.tv_newuser);
        etpassword = (EditText) findViewById(R.id.etpassword);
        etemail = (EditText) findViewById(R.id.etemail);

        tv_newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                tv_newuser.setVisibility(View.GONE);
                Intent i = new Intent(DistributorLoginActivity.this, AdminSignUpActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        if (mAuth.getCurrentUser() != null) {
            finish();
            Intent salesman = new Intent(context, Salesman.class);
            startActivity(salesman);
        }
    }


    //Submit Button method on OnClick
    public void submit_admin(View view) {
        tv_newuser.setVisibility(View.GONE);
        logintoUser();

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
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("MSG", "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();

                                Intent salesman = new Intent(context, Salesman.class);
                                startActivity(salesman);
                                //                            updateUI(user);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("MSG", "signInWithEmail:failure", task.getException());
                                Toast.makeText(DistributorLoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                //                          updateUI(null);
                            }

                            // ...
                        }
                    });

        }
    }

}
