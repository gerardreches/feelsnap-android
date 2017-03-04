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

import com.facebook.FacebookSdk;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FacebookAuthCredential;
import com.newproject.jordi.hackupc17.Services.FirebaseService;
import com.newproject.jordi.hackupc17.Singleton.Singleton;

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

        byte[] thephoto;

        Intent intent = getIntent();
        thephoto = intent.getByteArrayExtra("photo");

        Bitmap bitmap = BitmapFactory.decodeByteArray(thephoto, 0, thephoto.length);

        imgPhotoResult.setImageBitmap(bitmap);

        String dateTime  = DateFormat.format("yyyyMMddhhmmss", new Date().getTime()).toString();
        final String filename = "photos/"+ FacebookSdk.getClientToken()+"_"+dateTime;

        //FirebaseService.StoreImage(thephoto, dateTime);
        FirebaseService.StoreImage(thephoto, filename);
        Log.d("FIRENAME",filename);
        //Log.d("FIREURL",""+Singleton.getInstance().getStorageRef().child(firename).getDownloadUrl().getResult());

        //imgPhotoResult.setImageBitmap(intent.getByteArrayExtra("photo"));

        //Singleton.getInstance().getStorageRef().child(filename);
        Singleton.getInstance().getStorageRef().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                txtScore.setText((CharSequence) Singleton.getInstance().getStorageRef().child(filename).getDownloadUrl().getResult());
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
