package com.example.bottlespinnerdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;

import java.util.Random;


public class Theme2_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView bottle;
    private Random random = new Random();
    private int lastDir;
    private boolean spinning;
    RelativeLayout relativeLayout;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme2);

        bottle = findViewById(R.id.botttleId);
        relativeLayout = findViewById(R.id.RelativeLayoutId);


        getSupportActionBar().setTitle("Spin the Bottle");


        //status bar color change
        getWindow().setStatusBarColor(ContextCompat.getColor(Theme2_Activity.this, R.color.black));
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.custom)));


        drawerLayout = findViewById(R.id.drawerId);

        NavigationView navigationView = findViewById(R.id.navigationId);
        //show custom icon in navigation drawer -> start
        navigationView.setItemIconTintList(null);
        //end
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    //Bottle Start

    public void spinBottle(View view){
        if (!spinning){


            int newDir = random.nextInt(7200);//1800
            float pivotX = bottle.getWidth() /2;
            float pivotY = bottle.getHeight() /2;

            Animation rotate = new RotateAnimation(lastDir, newDir, pivotX, pivotY);
            rotate.setDuration(2500);//2500
            rotate.setFillAfter(true);
            rotate.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    spinning = true;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    spinning = false;

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            lastDir = newDir;
            bottle.startAnimation(rotate);

        }
    }
    //Bottle finish
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Intent intent;

        if (item.getItemId() == R.id.homeMenuId) {

            intent = new Intent(this, MainActivity.class);
            startActivity(intent);

        } else if (item.getItemId() == R.id.Theme2Id) {

            intent = new Intent(this,Theme1_Activity.class);
            startActivity(intent);

        } else if (item.getItemId() == R.id.Theme3Id) {

            intent = new Intent(this, Theme2_Activity.class);
            startActivity(intent);
        }

        //Share App
        try {

            if (item.getItemId() == R.id.shareId){
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Bottle Spinner Game");
                intent.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id="+getApplicationContext()
                        .getPackageName());
                startActivity(Intent.createChooser(intent,"Share With"));
            }
        }catch (Exception e){
            Toast.makeText(this, "Unable to share this App", Toast.LENGTH_SHORT).show();
        }

        //End of share App

        //Rate us
        if (item.getItemId() == R.id.Rate_UsId){
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id"+getApplicationContext().getPackageName());
            intent = new Intent(Intent.ACTION_VIEW,uri);
            try {
                startActivity(intent);
            }catch (Exception e){
                Toast.makeText(this, "Unable to open", Toast.LENGTH_SHORT).show();
            }
        }
        if (item.getItemId() == R.id.about_gameId){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutId,new AboutUSFragment()).commit();
            relativeLayout.setVisibility(View.GONE);
        }

        return false;
    }
    @Override
    public void onBackPressed() {

        Toast.makeText(this, "Back To Home Theme", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }
}