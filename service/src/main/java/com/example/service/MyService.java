package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private int count;      //充当服务的状态
    private boolean stop;   //确定是否停止count计数
    private MyBinder binder = new MyBinder();  //onBinder方法返回的对象

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("MyService", "服务创建成功onCreate()");
        new Thread() {
            @Override
            public void run() {
                while (!stop) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;

                }
            }
        }.start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.v("MyService", "服务绑定成功onBind()");
        return binder;
//        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.v("MyService", "服务解除绑定onUnbind()");
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.stop=true;
        Log.v("MyService", "服务解除onDestroy()");
    }


    public class MyBinder extends Binder {
        public int getCount() {
            //获取Service的运行状态
            return count;
        }
    }
}