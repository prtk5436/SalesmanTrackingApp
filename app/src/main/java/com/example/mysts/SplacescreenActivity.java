package com.example.mysts;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

public class SplacescreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splacescreen);

        Thread background = new Thread() {
            public void run() {

                try {
                    // Thread will sleep for 5 seconds
                    sleep(2 * 1000);

                    Intent intent = new Intent(SplacescreenActivity.this, UserSelectionActivity.class);
                    startActivity(intent);

                    // After 5 seconds redirect to another intent

                    //Remove activity
                    finish();

                } catch (Exception e) {

                }
            }
        };
        // start thread
        background.start();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }
}

