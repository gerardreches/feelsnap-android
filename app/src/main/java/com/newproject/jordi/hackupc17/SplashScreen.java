package com.newproject.jordi.hackupc17;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.facebook.AccessToken;

public class SplashScreen extends Activity {

    int millisecondsDelayed = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (AccessToken.getCurrentAccessToken() == null){
                    startActivity(new Intent(SplashScreen.this, ActivityLogin.class));
                }else{
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                }
            }
        }, millisecondsDelayed);
    }
}
