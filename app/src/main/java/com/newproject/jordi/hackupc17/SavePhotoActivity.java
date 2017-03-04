package com.newproject.jordi.hackupc17;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.Profile;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.newproject.jordi.hackupc17.Services.FirebaseService;

import java.util.Date;

public class SavePhotoActivity extends AppCompatActivity {

    TextView txtScore;
    ImageView imgPhotoResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_photo);
        imgPhotoResult = (ImageView) findViewById(R.id.img_photoresult);
        txtScore = (TextView) findViewById(R.id.txt_status);

        byte[] photo;

        Intent intent = getIntent();
        photo = intent.getByteArrayExtra("photo");

        Bitmap bitmap = BitmapFactory.decodeByteArray(photo, 0, photo.length);

        imgPhotoResult.setImageBitmap(bitmap);

        String dateTime  = DateFormat.format("yyyyMMddhhmmss", new Date().getTime()).toString();
        final String filename = "photos/" + Profile.getCurrentProfile().getId() + "_" + dateTime;

        FirebaseService.StoreImage(photo, filename);
        FirebaseStorage.getInstance().getReference().child(filename);

        FirebaseStorage.getInstance().getReference().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                txtScore.setText(uri.getPath());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
                Log.d("onFailure",""+exception);
            }
        });
    }
}
