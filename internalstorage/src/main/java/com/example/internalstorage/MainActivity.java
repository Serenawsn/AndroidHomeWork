package com.example.internalstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText studentNum;
    EditText studentName;
    Button saveButton;
    Button showSaveBtn;
    TextView showInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        studentNum = findViewById(R.id.StudentNum);
        studentName = findViewById(R.id.StudentName);
        saveButton = findViewById(R.id.saveInfoBtn);
        showSaveBtn = findViewById(R.id.showSaveInfoBtn);
        showInfo = findViewById(R.id.showInfoText);
        //保存个人信息
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //学号
                String number = studentNum.getText().toString();
                //姓名
                String name = studentName.getText().toString();
                String user_num = "学号为：" + number + "\n" + "姓名为：" + name;
                FileOutputStream filterOutputStream;
                //保存输入的数据
                try {
                    filterOutputStream = openFileOutput("user_num.txt", MODE_PRIVATE);//打开输入流，将数据存储到文件中
                    filterOutputStream.write(user_num.getBytes());
                    filterOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(MainActivity.this, "用户信息已保存", Toast.LENGTH_LONG).show();
            }
        });
        //显示保存的个人信息
        showSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //存储读取到的信息
                String mes = "";
                FileInputStream fileInputStream;
                try {
                    fileInputStream = openFileInput("user_num.txt");
                    byte[] buffer = new byte[512];
                    fileInputStream.read(buffer);
                    mes = new String(buffer);
//                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                showInfo.setText(mes);

            }
        });

    }
}