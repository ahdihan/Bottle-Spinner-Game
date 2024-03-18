package com.example.bottlespinnerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {

    TextView appname;
    LottieAnimationView lottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide(); //Hide the Action Bar

        //Hide the Status Bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_splash_screen);

        appname = findViewById(R.id.appname);
        lottie = findViewById(R.id.lottie);

        appname.animate().translationY(-1400).setDuration(2700).setStartDelay(1000);
        lottie.animate().translationY(3000).setDuration(2400).setStartDelay(2900);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();
            }
        },5000);
    }
}