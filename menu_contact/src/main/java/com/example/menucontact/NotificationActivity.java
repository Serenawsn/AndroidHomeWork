package com.example.menucontact;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class NotificationActivity extends AppCompatActivity {
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        textView = findViewById(R.id.receive_Text);
        textView.setText("仿微信菜单APP\n您的验证码为："+LoginActivity.code);
        //注册菜单
        registerForContextMenu(textView);
    }
    //重写上下文菜单的创建方法
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.sub_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.blue:
                textView.setTextColor(Color.BLUE);
                break;
            case R.id.green:
                textView.setTextColor(Color.GREEN);
                break;
            case R.id.red:
                textView.setTextColor(Color.RED);
                break;
            case R.id.yellow:
                textView.setTextColor(Color.YELLOW);
                break;
            case R.id.frontSize_10sp:
                textView.setTextSize(10);
                break;
            case R.id.frontSize_20sp:
                textView.setTextSize(30);
                break;
            case R.id.frontSize_30sp:
                textView.setTextSize(50);
                break;
            case R.id.frontSize_40sp:
                textView.setTextSize(70);
                break;
        }
        return super.onContextItemSelected(item);
    }
}