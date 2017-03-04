package com.newproject.jordi.hackupc17;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.newproject.jordi.hackupc17.Entities.User;
import com.newproject.jordi.hackupc17.Singleton.Singleton;

public class ActivityLogin extends AppCompatActivity {

    Button btnLogin, btnRanking;
    TextView txtLoginStatus;
    LoginButton loginButton;
    CallbackManager callbackManager;
    String TAG = "LOGINFB";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        txtLoginStatus = (TextView) findViewById(R.id.txt_login_fb);
        btnLogin = (Button) findViewById(R.id.btn_login_go);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        btnRanking = (Button) findViewById(R.id.btn_ranking);
        loginButton.setReadPermissions("email");
        callbackManager = CallbackManager.Factory.create();
        //LoadUI();

        btnRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityLogin.this,ActivityRanking.class);
                startActivity(intent);
            }
        });

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code

                //sharedPreferencesEditor.putString("userId",loginResult.getAccessToken().getUserId());
                //sharedPreferencesEditor.putString("tokenId",loginResult.getAccessToken().getToken());

                handleFacebookAccessToken(loginResult.getAccessToken());

                txtLoginStatus.setText("Login succes! \n"+
                loginResult.getAccessToken().getUserId()+
                "\n"+loginResult.getAccessToken().getToken());
                Log.d(TAG, "onSuccess: "+loginResult);

                Singleton.getInstance().setUser(new User(loginResult.getAccessToken().getUserId(),loginResult.getAccessToken().getToken()));



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

        firebaseAuth = firebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user!=null){
                    Intent intent = new Intent(ActivityLogin.this,MainActivity.class);
                    startActivity(intent);
                }
            }
        };

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityLogin.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void handleFacebookAccessToken(AccessToken accessToken) {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Error login",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.removeAuthStateListener(firebaseAuthListener);
    }

    private void LoadUI(){



    }
}
