package com.newproject.jordi.hackupc17;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.*;

public class ActivityLogin extends AppCompatActivity {

    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        LoadUI();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityLogin.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void LoadUI(){
        btnLogin = (Button) findViewById(R.id.btn_login_fb);
    }
}
