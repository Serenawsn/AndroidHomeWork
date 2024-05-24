package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText editUsername;
    EditText editUserPwd;
    CheckBox remember;
    Button loginBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editUsername = findViewById(R.id.editUsername);
        editUserPwd = findViewById(R.id.editUserPwd);
        remember = findViewById(R.id.rememberPwd);
        loginBtn = findViewById(R.id.login);

        SharedPreferences sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE);
        String usernameDate = sharedPreferences.getString("username", null);
        String userPwdDate = sharedPreferences.getString("userPwd", null);
        editUserPwd.setText(usernameDate);
        editUsername.setText(userPwdDate);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editUsername.getText().toString();
                String userPwd = editUserPwd.getText().toString();
                if (remember.isChecked()) {
                    //获取用户名和密码
                    //如果复选框被勾中，则使用sharedPreferences存储数据
                    SharedPreferences sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit(); //获取编译器
                    //MODE_PRIVATE私有存储，其他应用无法访问
                    editor.putString("username", username);//存入数据
                    editor.putString("userPwd", userPwd);
                    editor.apply(); //提交修改
                } else {
                    //复选框无选中，则不存储数据
                    SharedPreferences sharedPreferences = getSharedPreferences("userData",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear(); //删除所有数据
                    editor.apply(); //提交修改
                }
                Intent intent = new Intent(MainActivity.this,ServerActivity.class);
                startActivity(intent);
            }
        });


    }

}