package com.example.listview_test;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import java.util.List;

//将数据绑定到 ListView 中的每个列表项上。这个适配器继承自 ArrayAdapter，泛型类型为 FriendsInfo，表示它将用于管理 FriendsInfo 对象的列表。
public class ListViewAdapter extends ArrayAdapter<FriendsInfo> {
    //    private Activity mContext = null;   //上下文环境
    private int resourceId;             //列表项布局资源ID
//    private String[] items;             //列表内容数组

    ImageView imageView;
    TextView name;
    TextView message;
    TextView time;
    TextView info;


    public ListViewAdapter(MainActivity context, int items_layout, List<FriendsInfo> friendsInfoList) {
        super(context, items_layout, friendsInfoList);
        //保存参数
        resourceId = items_layout;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        /*position：当前要渲染的列表项在列表中的位置。
        convertView：已经存在的视图，用于重复利用以提高性能。
        parent：列表项所在的父容器，即 ListView。
        */
        //用于获取列表中指定位置的数据项
        FriendsInfo friendsInfo = getItem(position);
        //引用最终要返回的列表项视图
        View view;
        if (convertView == null) {
            //初次加载view
            //获取Item布局视图
            /*指定的布局资源（resourceId）转换为视图对象。
            其中，resourceId 是要加载的布局资源的 ID，parent 是要将视图添加到的父容器，false 表示不将生成的视图添加到父容器中，因为在这个方法中，我们会将其返回，而不是直接添加到父容器中。*/
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);

        } else {
            //非初次加载，直接复用View
            view = convertView;
        }
        //找到Item布局上的控件
        imageView = view.findViewById(R.id.imageView);
        name = view.findViewById(R.id.name);
        message = view.findViewById(R.id.message);
        info = view.findViewById(R.id.info);
        time = view.findViewById(R.id.time);
        CardView cardView = view.findViewById(R.id.cardViewTest);

        //将FriendsInfo的数据绑定到View中的各个控件上显示
        imageView.setImageResource(friendsInfo.getImageId());
        name.setText(friendsInfo.getName());
        message.setText(friendsInfo.getMessage());
        time.setText(friendsInfo.getTime());
        //friendsInfo.getInfo() 返回的是一个整数而不是一个字符串。
        //为了解决这个问题，你可以将 friendsInfo.getInfo() 返回的整数转换成字符串，然后再设置给 info 的 TextView。你可以使用 String.valueOf() 方法将整数转换为字符串。
        if (friendsInfo.getInfo() == 0) {
            cardView.setVisibility(View.INVISIBLE);
        } else {
            cardView.setVisibility(View.VISIBLE);
            info.setText(String.valueOf(friendsInfo.getInfo()));
        }

        //返回Item视图
        return view;
    }
}
