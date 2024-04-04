package com.example.intent_turn_info;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button1;
    Button button2;
    String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getActionBar();
        //获取组件id
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        //button1的点击事件
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddressActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        //button2的点击事件
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, AddressInfo.class);
                intent2.putExtra("address", address);
                intent2.putExtra("info", "翁烁柠+2313202060704");
                startActivity(intent2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 2) {
            address = data.getStringExtra("address");
            button1.setText(address);
        }
    }

}