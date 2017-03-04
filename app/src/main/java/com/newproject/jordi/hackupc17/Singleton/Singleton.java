package com.newproject.jordi.hackupc17.Singleton;

import com.facebook.Profile;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.newproject.jordi.hackupc17.Entities.User;

/**
 * Created by jordi on 4/3/17.
 */
public class Singleton {
    private User user;
    StorageReference storageRef;
    StorageReference httpsReference;

    private static Singleton ourInstance = new Singleton();

    public static Singleton getInstance() {
        return ourInstance;
    }

    private Singleton() {
        storageRef = FirebaseStorage.getInstance().getReferenceFromUrl("gs://hackupc17.appspot.com");
        if (!Profile.getCurrentProfile().getId().isEmpty()){
            user = new User(Profile.getCurrentProfile().getId());
        }

    }

    public User getUser() {
        return user;
    }

    public StorageReference getStorageRef() {
        return storageRef;
    }



    public void setUser(User user) {
        this.user = user;
    }
}
