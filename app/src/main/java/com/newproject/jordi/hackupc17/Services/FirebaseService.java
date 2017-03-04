package com.newproject.jordi.hackupc17.Services;

import com.google.firebase.storage.UploadTask;
import com.newproject.jordi.hackupc17.Singleton.Singleton;

/**
 * Created by jordi on 4/3/17.
 */

public class FirebaseService {



    public static UploadTask StoreImage(byte[] data, String filename){
        return Singleton.getInstance().getStorageRef().child(filename).putBytes(data);
    }

}
