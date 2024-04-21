package com.example.menucontact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

public class LoginActivity extends AppCompatActivity {
    EditText editTextName;  //用户名
    EditText editTextPwd;   //密码
    EditText editTextCode;  //验证码
    Button sendButton;      //发送验证码
    Button loginButton;     //登录按钮
    public static int code;     //全局验证码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextName = findViewById(R.id.edit_name);
        editTextPwd = findViewById(R.id.edit_pwd);
        editTextCode = findViewById(R.id.edit_code);
        sendButton = findViewById(R.id.sendCode);
        loginButton = findViewById(R.id.login_Btn);

        //发送验证码
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                code = random.nextInt(9000)+1000;
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                //发送通知的代码Android 8.0以下版本
               /*
                Intent intent = new Intent(MainActivity.this, NotificationActivity.class);//构建Intent对象，用于活动跳转
                PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);//构建PendingIntent对象，用于点击通知后做跳转
                Notification notification = new NotificationCompat.Builder(MainActivity.this)//android7.0以下创建通知对象
                        .setContentTitle("This is content title")//通知标题
                        //通知的详细文本信息
                        .setContentText("This is content text  This is content text This is content text This is content text This is content text This is content text This is content text")
                        .setWhen(System.currentTimeMillis())//什么时候弹出通知信息
                        .setSmallIcon(R.mipmap.ic_launcher)//设置小图标
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))//设置大图标
                        .setContentIntent(pi)//设置跳转需要用到的PendingIntent对象
                        // .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/XXX")))//声音
                        // .setVibrate(new long[]{0, 1000, 1000, 1000,1000,1000})//震动，每隔一秒震动一次
                        // .setLights(Color.GREEN, 1000, 1000)//呼吸灯每隔一秒闪烁一次
                        // .setDefaults(NotificationCompat.DEFAULT_ALL)
                        //设置多行文本信息
                        //.setStyle(new NotificationCompat.BigTextStyle().bigText("Learn how to build notifications, send and sync data, and use voice actions. Get the official Android IDE and developer tools to build apps for Android."))
                        //通知里面引入超大图片
                        //.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.image)))
                        .setPriority(NotificationCompat.PRIORITY_MAX)//设置优先级
                        .setAutoCancel(true)//设置点击通知后通知是否消失，true为消失
                        .build();
                manager.notify(1, notification);//通知管理器管理通知并发送通知
                */
                //发送通知，Android8.0及以上版本
                if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){ //在这里判断系统版本是否大于8.0，大于8.0则创建一个Channel
                    //构建通知渠道对象，每个通知都需要依附于一个通知渠道，三个参数分别表示渠道ID、渠道名称（用于可以根据名称选择是否允许弹出通知）、通知的优先级。
                    NotificationChannel notificationChannel = new NotificationChannel("channel","channelName",NotificationManager.IMPORTANCE_HIGH);
                    //通知管理器管理渠道，创建通知渠道
                    manager.createNotificationChannel(notificationChannel);
                }
                Intent notificationIntent = new Intent(LoginActivity.this, NotificationActivity.class);//构建Intent对象，用于活动跳转
                PendingIntent pi = PendingIntent.getActivity(LoginActivity.this, 0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);//构建PendingIntent对象，用于点击通知后做跳转
                Notification notification = new NotificationCompat.Builder(LoginActivity.this,"channel")//创建通知对象，第二个参数表示通知渠道的id，用于将当前的通知和渠道进行绑定
                        .setContentTitle("仿微信菜单APP")//通知标题
                        .setContentText("您的验证码为："+code)//通知内容
                        .setWhen(System.currentTimeMillis())//时间
                        .setSmallIcon(R.drawable.bee)//设置小图标
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.img))//设置大图标
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.img))) //通知里面引入超大图片
                        .setContentIntent(pi)//设置跳转需要用到的PendingIntent对象
                        .setAutoCancel(true)//设置点击通知后通知是否消失，true为消失
                        .build();
                manager.notify(1, notification);//通知管理器管理通知并发送通知

            }
        });
    }
}