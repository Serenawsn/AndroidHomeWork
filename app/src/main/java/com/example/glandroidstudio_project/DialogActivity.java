package com.example.glandroidstudio_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class DialogActivity extends AppCompatActivity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        Log.i("DialogActivity","onCreate()");
        button = findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DialogActivity.this,FirstActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("DialogActivity","onStart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("DialogActivity","onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("DialogActivity","onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("DialogActivity","onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("DialogActivity","onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("DialogActivity","onDestroy()");
    }

}