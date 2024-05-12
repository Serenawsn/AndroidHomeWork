package com.example.broadcast1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class OderedBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
//        throw new UnsupportedOperationException("Not yet implemented");
        String message = intent.getStringExtra("message");
        Toast.makeText(context, "OderedBroadcastReceiver接收到了有序广播", Toast.LENGTH_LONG).show();
        Toast.makeText(context, "OderedBroadcastReceiver:" + message, Toast.LENGTH_SHORT).show();
        abortBroadcast();
    }
}