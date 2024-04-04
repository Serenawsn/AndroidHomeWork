package com.example.glandroidstudio_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {
    Button button;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Log.i("FirstActivity", "onCreate()");
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(FirstActivity.this, DialogActivity.class);
                startActivity(intent2);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("FirstActivity", "onStart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("FirstActivity", "onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("FirstActivity", "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("FirstActivity", "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("FirstActivity", "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("FirstActivity", "onDestroy()");
    }
}