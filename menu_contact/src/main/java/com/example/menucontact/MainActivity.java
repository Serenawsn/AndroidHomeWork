package com.example.menucontact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    //定义朋友列表
    List<FriendsInfo> friendsInfoList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取数据
        initData();
        listView = findViewById(R.id.listView);
        //创建适配器
        ListViewAdapter listViewAdapter = new ListViewAdapter(MainActivity.this,R.layout.item_layout,friendsInfoList);
        //为listview添加适配器
        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FriendsInfo friendsInfo = friendsInfoList.get(i);
                CardView cardView = view.findViewById(R.id.cardView);
                cardView.setVisibility(View.INVISIBLE);
                Toast.makeText(MainActivity.this,"您已选择第"+(i+1)+"个item",Toast.LENGTH_LONG).show();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.wechat_menu, menu);
        return true;
    }

    //在选项菜单打开以后会调用这个方法，设置menu图标显示（icon）
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.createChat:
                Toast.makeText(MainActivity.this,"创建群聊",Toast.LENGTH_LONG).show();
                break;
            case R.id.addFriends:
                Toast.makeText(MainActivity.this,"添加好友",Toast.LENGTH_LONG).show();
                break;
            case R.id.matchChat:
                Toast.makeText(MainActivity.this,"匹配聊天",Toast.LENGTH_LONG).show();
                break;
            case R.id.togetherParty:
                Toast.makeText(MainActivity.this,"一起派对",Toast.LENGTH_LONG).show();
                break;
            case R.id.scan:
                Toast.makeText(MainActivity.this,"扫一扫",Toast.LENGTH_LONG).show();
                break;
            case R.id.faceToFace:
                Toast.makeText(MainActivity.this,"面对面快传",Toast.LENGTH_LONG).show();
                break;
            case R.id.payMoney:
                Toast.makeText(MainActivity.this,"收付款",Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
        return true;
    }

    private void initData() {
        //图片
        int[] images = new int[]{R.drawable.image0,R.drawable.image1,R.drawable.image2,R.drawable.image3,
                R.drawable.image4,R.drawable.image5, R.drawable.image6,R.drawable.image7,R.drawable.image8,
                R.drawable.image9,R.drawable.image10,R.drawable.image11};
        //名字
        String[] name = new String[]{"张三", "李四", "王武", "赵柳", "曾泽明", "末广乔", "橙红", "琳琳", "阿里", "萨奥", "王鹏","李知恩"};
        //消息
        String[] message = new String[]{"吃饭了吗", "中午吃什么", "要去哪玩", "什么时候出发", "在吗？", "一起学习", "晚上一起吃饭不", "一起学Android", "有没有吃早餐", "到哪啦", "哈哈哈哈", "我到了", "有事微信联系"};

        for (int i = 0;i < 5; i++){
            for (int j = 0; j < 10; j++){
                //时间
                Random random = new Random();
                long r = random.nextLong();
                //创建SimpleDateFormat实例
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                Date date = new Date(r);
                //格式化时间
                String time = simpleDateFormat.format(date);
                //随机生成消息个数
                int info = random.nextInt(99);
                //创建一个朋友消息实例
                FriendsInfo friendsInfo = new FriendsInfo(images[random.nextInt(images.length)],name[random.nextInt(11)],message[random.nextInt(13)],time,info);
                friendsInfoList.add(friendsInfo);
            }
        }
    }
}