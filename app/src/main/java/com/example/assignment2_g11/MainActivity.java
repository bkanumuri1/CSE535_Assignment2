package com.example.assignment2_g11;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static int CAMERA_PERMISSION_CODE =100;
    private static int VIDEO_RECORD_CODE = 101;
    private Uri videoPath;


    public Button button;




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
        button = (Button) findViewById(R.id.NextButton);
        button.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View n) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                startActivity(intent);

            }
        });


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
        startActivityForResult(intent, VIDEO_RECORD_CODE);

    }

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
    public void UploadButtonPressed(View v){
        Toast.makeText(MainActivity.this,"Uploading to the server", Toast.LENGTH_SHORT).show();
    }




}