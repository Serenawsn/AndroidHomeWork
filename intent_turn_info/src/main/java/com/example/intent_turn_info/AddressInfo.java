package com.example.intent_turn_info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AddressInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_info);
        TextView textView = findViewById(R.id.textView1);
        Intent intent3 = getIntent();
        textView.setText("收到数据:\n personalInfomation:" + intent3.getStringExtra("info") + "\n" + "myAddress:" + intent3.getStringExtra("address"));

    }
}