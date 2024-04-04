package com.example.glandroidstudio_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.i("SecondActivity","onCreate()");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("SecondActivity","onStart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("SecondActivity","onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("SecondActivity","onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("SecondActivity","onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("SecondActivity","onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("SecondActivity","onDestroy()");
    }
}