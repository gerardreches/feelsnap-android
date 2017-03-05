package com.newproject.jordi.hackupc17;

import android.app.Application;

import com.google.firebase.FirebaseApp;

/**
 * Created by jordi on 4/3/17.
 */

public class HackUpc17App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseApp.initializeApp(this);
    }

}
