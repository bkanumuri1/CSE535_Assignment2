package com.example.assignment2_g11;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        TextView Default = (TextView) findViewById(R.id.textView);
        Default.setText("Choose an Algorithm!");
    }
    public void LocationBasedPressed(View A1){
        Toast.makeText(Activity2.this,"Selected Location Based Algorithm", Toast.LENGTH_SHORT).show();
    }
    public void MovementBasedPressed(View A2){
        Toast.makeText(Activity2.this,"Selected Movement Based Algorithm", Toast.LENGTH_SHORT).show();
    }
    public void HandshapeBasedPressed(View A3){
        Toast.makeText(Activity2.this,"Selected Handshake Based Algorithm", Toast.LENGTH_SHORT).show();
    }
}