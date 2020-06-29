package com.example.workfitt;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {
    static int SPLASH_TIME = 950;
    Animation topAnimation,bottomAnimation,fadeOut;
    ImageView fire_Logo,dumbell_Logo,fakeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        topAnimation= AnimationUtils.loadAnimation(this,R.anim.fire_animation_top);
        bottomAnimation = AnimationUtils.loadAnimation(this,R.anim.dumbell_animation_bottom);
        fadeOut = AnimationUtils.loadAnimation(this,R.anim.fade_out);
        fire_Logo = findViewById(R.id.fire_logo);
        dumbell_Logo = findViewById(R.id.dumbell_logo);
        fakeView = findViewById(R.id.fakeView);
        fire_Logo.setAnimation(topAnimation);
        dumbell_Logo.setAnimation(bottomAnimation);
        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                Pair[] pairs = new Pair[3];
                pairs[0] = new Pair<View,String>(fire_Logo,"logo_move_up");
                pairs[1] = new Pair<View,String>(dumbell_Logo,"logo_move_up");
                pairs[2] = new Pair<View,String>(fakeView,"logo_move_up"); //just to ensure that logo's don't push down before going up
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashScreen.this,pairs);
                    startActivity(intent,options.toBundle());
                    finish();
                }
                else {
                    startActivity(intent);
                    finish();
                }
            }
        },SPLASH_TIME);
    }
}
