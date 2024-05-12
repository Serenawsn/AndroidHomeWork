package com.example.broadcast1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    //自定义的网络广播
    private NetworkReceiver networkReceiver;
    //IntentFilter 是 Android 中用于过滤 Intent 的一个类,用于指定您想要接收的广播的类型。
    private IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intentFilter = new IntentFilter();
        //指定想要接收的广播动作,这里是是网络状态变化的 Intent
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkReceiver = new NetworkReceiver();
        //注册广播
        registerReceiver(networkReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册广播
        unregisterReceiver(networkReceiver);
    }
}