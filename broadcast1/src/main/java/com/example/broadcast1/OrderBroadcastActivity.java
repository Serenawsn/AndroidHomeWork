package com.example.broadcast1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OrderBroadcastActivity extends AppCompatActivity {

    Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_broadcast);
        sendButton = findViewById(R.id.sendBtn);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("com.example.sendoderedbroadcast.MY_BROADCAST");
                intent.addFlags(0x01000000);
//                intent.setComponent(new ComponentName("com.example.broadcast1", "com.example.broadcast1.OderedBroadcastReceiver"));
//                intent.setComponent(new ComponentName("com.example.broadcast1", "com.example.broadcast1.AnotherBroadcastReceiver"));
                //自定义一条有序广播
                intent.putExtra("message", "恭喜wsn succeed!");
                //有序广播
                sendOrderedBroadcast(intent, null);

            }
        });
    }
}