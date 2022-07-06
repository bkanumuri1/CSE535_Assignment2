package com.example.assignment2_g11;

import static android.app.ProgressDialog.show;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//import com.android.volley.AuthFailureError;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.toolbox.StringRequest;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


//import okhttp3.Call;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class MainActivity extends AppCompatActivity {
    private static int CAMERA_PERMISSION_CODE =100;
    private static int VIDEO_RECORD_CODE = 101;
    private Uri videoPath;


    public Button button;
    MediaRecorder recorder;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (isCameraPresentInPhone()) {
            Log.i("VIDEO_RECORD_TAG","Camera is detected");
            getCameraPermission();
        }
        else{
            Log.i ("VIDEO_RECORD_TAG", "No Camera is detected" );
        }

    }

    public void recordButtonPressed(View view){
        recordVideo();
    }
    private boolean isCameraPresentInPhone(){
        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
            return true;
        }
        else{
            return false;
        }
    }

    private void getCameraPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this,new String[]
                    {Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE );
        }

    }

    private void recordVideo(){
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        //recorder.setMaxDuration(500);
        Intent recordIntent= new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        recordIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT,5);
        recordIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        recordIntent.putExtra("android.intent.extras.CAMERA_FACING", 1);
        startActivityForResult(intent, VIDEO_RECORD_CODE);

//        makePOSTRequest();

        //recorder.setMaxDuration(500); // 50 seconds
        //recorder.setMaxFileSize(5000000); // Approximately 5 megabytes


//        MediaType formData = MediaType.get("form-data");
//        RequestBody body = RequestBody.create("", formData);
//        Request request = new Request.Builder()
//                .url("http://localhost:3001/upload")
//                .build();
//
//        postRequest("http://localhost:3001/upload", null);

    }

    public void postRequest(String postUrl, RequestBody httpRequestBody){
        OkHttpClient client = new OkHttpClient();

        MediaType mp4 = 
        Request request = new Request.Builder()
                .url(postUrl)
                .post(httpRequestBody)
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                call.cancel();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Failed to connect to server", Toast.LENGTH_SHORT).show();
                    }
                });
                Log.e("HttpService", "onFailure() Request was:" + request);
                e.printStackTrace();
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                Log.e("response", "onResponse(): " + response);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "Video Uploaded to server", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            }
        });

    }

//    public void makePOSTRequest() {
//
//        Toast.makeText(MainActivity.this, "Uploading to the server", Toast.LENGTH_SHORT).show();
//        String url = "http://localhost:3001/upload";
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                response -> Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_LONG).show(),
//                error -> Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show()) {
//
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//
//
//                Toast.makeText(MainActivity.this, "Successfully uploaded to server", Toast.LENGTH_SHORT).show();
//
//                return params;
//            }
//        }
//    }
//




    /*
    private void recordVideo() {
        MediaRecorder recorder = null;
        recorder.setVideoSource(MediaRecorder.VideoSource.DEFAULT);


        recorder.setMaxDuration(500); // 50 seconds
        recorder.setMaxFileSize(5000000); // Approximately 5 megabytes
    }
    */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == VIDEO_RECORD_CODE){


            if (resultCode == RESULT_OK) {
                videoPath = data.getData();
                Log.i("VIDEO_RECORD_TAG", "Video is available at path" + videoPath);
            }
            else if (resultCode == RESULT_CANCELED){
                Log.i("VIDEO_RECORD_TAG", "Recorded video is Cancelled");
            }
            else {
                Log.i("VIDEO_RECORD_TAG", "Recorded video has some error" );


            }

        }

    }




}