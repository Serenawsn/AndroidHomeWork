package com.example.listview_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    //创建列表，用于存储好友的信息
    List<FriendsInfo> friendsInfoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化数据
        initData();

        listView = findViewById(R.id.listView);
        //实例化ListViewAdapter对象
        ListViewAdapter listViewAdapter = new ListViewAdapter(MainActivity.this, R.layout.items_layout, friendsInfoList);
        //对listView设置适配器
        listView.setAdapter(listViewAdapter);
        //点击显示item相关信息
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //从列表中获取了第 i 个位置的好友信息对象。
                FriendsInfo friendsInfo = friendsInfoList.get(i);
                //CardView 在 MainActivity 的 XML 文件中没有定义，但在 item_layout.xml 中定义了，并且可以通过适配器在 MainActivity 中使用。
                CardView cardView = view.findViewById(R.id.cardViewTest);
                //若点击item则小红点不显示
                cardView.setVisibility(View.INVISIBLE);
                Toast.makeText(MainActivity.this, "您点击了第" + (i + 1) + "个item" + friendsInfo.getName(), Toast.LENGTH_LONG).show();

            }
        });
    }

    private void initData() {
        //照片
        int[] imageId = new int[]{R.drawable.image1, R.drawable.image2, R.drawable.image3,
                R.drawable.image4, R.drawable.image5, R.drawable.image6, R.drawable.image7,
                R.drawable.image8, R.drawable.image9, R.drawable.image10, R.drawable.image11,
                R.drawable.image12, R.drawable.image13, R.drawable.image14, R.drawable.image15
        };
        //姓名
        String[] name = new String[]{"张三", "李四", "王武", "赵柳", "曾泽明", "末广乔", "橙红", "琳琳", "阿里", "萨奥", "王鹏"};
        //消息
        String[] message = new String[]{"吃饭了吗", "中午吃什么", "要去哪玩", "什么时候出发", "在吗？", "一起学习", "晚上一起吃饭不", "一起学Android", "有没有吃早餐", "到哪啦", "哈哈哈哈", "我到了", "有事微信联系"};

        for (int i = 0; i <= 5; i++) {
            for (int j = 0; j < 10; j++) {
                //创建一个随机数生成器
                Random random = new Random();
                long r = random.nextLong();
                //创建SimpleDateFormat实例
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                //获取时间
                Date date = new Date(r);
                //格式化时间
                String time = simpleDateFormat.format(date);
                //头像随机
                int image = random.nextInt(imageId.length);
                //info-随机数
                int info = random.nextInt(40);
                //创建了一个新的 FriendsInfo 对象
                FriendsInfo friendsInfo = new FriendsInfo(imageId[image], name[random.nextInt(11)], message[random.nextInt(11)], time, info);
//                将新创建的 FriendsInfo 对象 friendsInfo 添加到 friendsInfoList 列表中，
                friendsInfoList.add(friendsInfo);
            }
        }
    }


}