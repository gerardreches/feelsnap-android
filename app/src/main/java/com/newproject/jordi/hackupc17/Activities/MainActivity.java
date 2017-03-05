package com.newproject.jordi.hackupc17.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.newproject.jordi.hackupc17.R;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    Button btnLaunchCamera;
    private Toolbar toolbar;
    int CAMERA_REQUEST = 1888;
    Bitmap photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        setTitle("Hola");*/


        LoadUI();

        btnLaunchCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                //startActivityForResult(intent, 0);

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra("putSomething",true);
                startActivityForResult(cameraIntent,CAMERA_REQUEST);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            this.photo = (Bitmap) data.getExtras().get("data");

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 90, stream);
            byte[] image = stream.toByteArray();

            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("photo", image);
            startActivity(intent);
        }
    }

    private void LoadUI(){
        btnLaunchCamera = (Button) findViewById(R.id.btn_launchcamera);
    }
}
