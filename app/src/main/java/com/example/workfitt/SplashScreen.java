package com.example.workfitt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    Animation topAnimation,bottomAnimation;
    ImageView fire_Logo,dumbell_Logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        topAnimation= AnimationUtils.loadAnimation(this,R.anim.fire_animation_top);
        bottomAnimation = AnimationUtils.loadAnimation(this,R.anim.dumbell_animation_bottom);
        fire_Logo = findViewById(R.id.fire_logo);
        dumbell_Logo = findViewById(R.id.dumbell_logo);
        fire_Logo.setAnimation(topAnimation);
        dumbell_Logo.setAnimation(bottomAnimation);
    }
}
