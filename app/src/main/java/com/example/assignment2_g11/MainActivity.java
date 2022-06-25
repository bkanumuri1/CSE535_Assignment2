package com.example.assignment2_g11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button recordButton;
    private static int VIDEO_REQUEST= 201;
    String realPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recordButton=findViewById(R.id.recordButton);

        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordVideo(view);
            }
        });
    }

    public void recordVideo(View view) {

        Intent recordIntent= new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        recordIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT,5);
        recordIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        recordIntent.putExtra("android.intent.extras.CAMERA_FACING", 1);
        startActivityForResult(recordIntent, VIDEO_REQUEST);


    }

    public String getPathFromURI(Context context, Uri contentUri) {

        if ( contentUri.toString().indexOf("file:///") > -1 ){
            return contentUri.getPath();
        }

        Cursor thisCursor = null;
        try {
            String[] temp = { MediaStore.Images.Media.DATA };
            thisCursor = context.getContentResolver().query(contentUri,  temp, null, null, null);
            int column_index = thisCursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            thisCursor.moveToFirst();
            return thisCursor.getString(column_index);
        }finally {
            if (thisCursor != null) {
                thisCursor.close();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VIDEO_REQUEST && resultCode == RESULT_OK && data!=null ) {
            if (data.getData() != null) {
                Uri uri = data.getData();
                realPath=getPathFromURI(getApplicationContext(),uri);
                Toast.makeText(this, "Video has been saved to:\n" +
                        data.getData(), Toast.LENGTH_LONG).show();
            }
        }
        else if (requestCode == VIDEO_REQUEST && resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Video recording cancelled.",
                    Toast.LENGTH_LONG).show();
        } else if(requestCode == VIDEO_REQUEST) {
            Toast.makeText(this, "Failed to record video",
                    Toast.LENGTH_LONG).show();
        }
    }
}