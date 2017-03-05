package com.newproject.jordi.hackupc17.Application;

import android.app.Application;

import com.google.firebase.FirebaseApp;

/**
 * Created by jordi on 4/3/17.
 */

public class Singleton extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseApp.initializeApp(this);
    }

}
