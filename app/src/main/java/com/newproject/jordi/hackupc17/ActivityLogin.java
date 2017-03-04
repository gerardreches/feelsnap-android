package com.newproject.jordi.hackupc17;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.newproject.jordi.hackupc17.Entities.User;
import com.newproject.jordi.hackupc17.Singleton.Singleton;

public class ActivityLogin extends AppCompatActivity {

    Button btnLogin;
    TextView txtLoginStatus;
    LoginButton loginButton;
    CallbackManager callbackManager;
    String TAG = "LOGINFB";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        txtLoginStatus = (TextView) findViewById(R.id.txt_login_fb);
        btnLogin = (Button) findViewById(R.id.btn_login_go);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        callbackManager = CallbackManager.Factory.create();
        //LoadUI();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code

                //sharedPreferencesEditor.putString("userId",loginResult.getAccessToken().getUserId());
                //sharedPreferencesEditor.putString("tokenId",loginResult.getAccessToken().getToken());

                txtLoginStatus.setText("Login succes! \n"+
                loginResult.getAccessToken().getUserId()+
                "\n"+loginResult.getAccessToken().getToken());
                Log.d(TAG, "onSuccess: "+loginResult);

                Singleton.getInstance().setUser(new User(loginResult.getAccessToken().getUserId(),loginResult.getAccessToken().getToken()));

                Intent intent = new Intent(ActivityLogin.this,MainActivity.class);
                startActivity(intent);

            }

            @Override
            public void onCancel() {
                // App code
                Log.d(TAG, "onCancel: ");
                txtLoginStatus.setText("Login Cancelled");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.d(TAG, "onError: "+exception);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityLogin.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    private void LoadUI(){



    }
}
