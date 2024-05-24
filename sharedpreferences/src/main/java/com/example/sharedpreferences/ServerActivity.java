package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class ServerActivity extends AppCompatActivity {
    Button read;
    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);
        read = findViewById(R.id.read);
        message = findViewById(R.id.message);

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE);
                String usernameDate = sharedPreferences.getString("username", null);
                String userPwdDate = sharedPreferences.getString("userPwd", null);
                message.setText("用户名：" + usernameDate + "\n" + "密码：" + userPwdDate);
            }
        });
    }
}