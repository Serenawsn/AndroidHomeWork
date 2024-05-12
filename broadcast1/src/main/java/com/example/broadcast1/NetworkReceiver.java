package com.example.broadcast1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetworkReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "网络状态改变了", Toast.LENGTH_SHORT).show();
//        //安卓中获取系统服务
//        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//        // isAvailable():判断网络是否有效可用。
//        if (networkInfo!=null && networkInfo.isAvailable()){
//            Toast.makeText(context, "网络状态改变-网络已连接", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(context, "网络状态改变-网络未连接", Toast.LENGTH_SHORT).show();
//        }
    }
}
