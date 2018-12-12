package com.bedel.app.kamerbiz.Activity.Boot;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bedel.app.kamerbiz.Activity.Login.LoginActivity;
import com.bedel.app.kamerbiz.R;




public class BootActivity extends AppCompatActivity {
    ImageView _backgroundImageBiew;
    public ImageView _logoImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_boot);

        _logoImageView = (ImageView) findViewById(R.id.logoImageView);
        _backgroundImageBiew = (ImageView) findViewById(R.id.backgroundImageView);

        Animation logoAnimation = AnimationUtils.loadAnimation(this, R.anim.splascreen_logo_scale);
        Animation backgroundAnimation= AnimationUtils.loadAnimation(this, R.anim.splascreen_background_scale);

        logoAnimation.reset();
        _logoImageView.clearAnimation();
        _logoImageView.startAnimation(logoAnimation);

        logoAnimation.reset();
        _backgroundImageBiew.clearAnimation();
        _backgroundImageBiew.startAnimation(backgroundAnimation);

        openMainActivity();
    }

    public void openMainActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(BootActivity.this, LoginActivity.class));
                finish();
            }
        }, 3000);
    }
}
