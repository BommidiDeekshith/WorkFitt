package com.example.workfitt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    Animation slideInFromTop,fadeIn;
    TextView workfitt_branding;
    ImageView round_logo;
    LinearLayout signin_layout1,signin_layout2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        round_logo = findViewById(R.id.round_logo);
        workfitt_branding = findViewById(R.id.workfit_branding);
        signin_layout1 = findViewById(R.id.signin_option1);
        signin_layout2 = findViewById(R.id.signin_option2);
        slideInFromTop = AnimationUtils.loadAnimation(this, R.anim.slide_in_from_top_workfitt_branding);
        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in_signin_inputs);
        workfitt_branding.setAnimation(slideInFromTop);
        signin_layout1.setAnimation(fadeIn);
        signin_layout2.setAnimation(fadeIn);
    }
}
