package com.deaenita.asasta.view;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import com.deaenita.asasta.MainActivity;
import com.deaenita.asasta.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SplashscreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);



        new Thread() {
            public void run() {
                try {
                    sleep(2000);
                    startActivity(new Intent(SplashscreenActivity.this, MainActivity.class));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


}

