package com.newproject.jordi.hackupc17.Activities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.facebook.Profile;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.storage.UploadTask;
import com.google.gson.Gson;
import com.newproject.jordi.hackupc17.Application.Singleton;
import com.newproject.jordi.hackupc17.Entities.EmotionScore;
import com.newproject.jordi.hackupc17.R;
import com.newproject.jordi.hackupc17.Services.FirebaseService;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ResultActivity extends AppCompatActivity {

    TextView textScore;
    ImageView photoImageView;
    Singleton singleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        singleton = (Singleton) getApplication();

        photoImageView = (ImageView) findViewById(R.id.img_photoresult);
        textScore = (TextView) findViewById(R.id.txt_status);

        byte[] photo = getIntent().getByteArrayExtra("photo");
        Bitmap bitmap = BitmapFactory.decodeByteArray(photo, 0, photo.length);

        photoImageView.setImageBitmap(bitmap);

        storeImage(photo, generateFilename());
    }

    private void emotionsReceived(EmotionScore emotionScore){
        textScore.setText("" + (emotionScore.happiness * 1000));
    }

    private void storeImage(byte[] image, String filename) {

        UploadTask uploadTask = FirebaseService.StoreImage(image, filename);

        uploadTask.addOnSuccessListener(ResultActivity.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {

            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                String imageDownloadUrl = taskSnapshot.getDownloadUrl().toString();
                singleton.addToRequestQueue(requestEmotions(imageDownloadUrl));
            }

        }).addOnFailureListener(new OnFailureListener() {

            @Override
            public void onFailure(@NonNull Exception exception) {
                FirebaseCrash.report(new Throwable("Error getting image download URL"));
            }

        });
    }

    private JsonArrayRequest requestEmotions(final String imageDownloadUrl) {
        String endpoint = "https://westus.api.cognitive.microsoft.com/emotion/v1.0/recognize";

        return new JsonArrayRequest (Request.Method.POST, endpoint, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                EmotionScore emotionScore = null;
                try {
                    emotionScore = new Gson().fromJson(response.get(0).toString(), EmotionScore.class);
                } catch (JSONException e) {
                    FirebaseCrash.report(new Throwable(e.toString()));
                }

                emotionsReceived(emotionScore);
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                FirebaseCrash.report(new Throwable("Emotions request failed: " + error.toString()));
            }

        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                params.put("Ocp-Apim-Subscription-Key", "979efac69ded485cb2643f3e5916d3a0");
                return params;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("url", imageDownloadUrl);
                return params;
            }
        };
    }

    private String generateFilename() {
        return Profile.getCurrentProfile().getId()
                + "_"
                + DateFormat.format("yyyyMMddhhmmss", new Date().getTime()).toString()
                + ".png";
    }
}
