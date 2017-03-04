package com.newproject.jordi.hackupc17.Singleton;

import com.facebook.Profile;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.newproject.jordi.hackupc17.Entities.User;

/**
 * Created by jordi on 4/3/17.
 */
public class Singleton {

    private static Singleton ourInstance = new Singleton();

    public static Singleton getInstance() {
        return ourInstance;
    }

    private Singleton() {
        //
    }

}
