package com.newproject.jordi.hackupc17.Services;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

/**
 * Created by jordi on 4/3/17.
 */

public class FirebaseService {

    public static UploadTask StoreImage(byte[] data, String filename){
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        StorageReference storageReference = firebaseStorage.getReference(filename);

        return storageReference.putBytes(data);
    }

}
