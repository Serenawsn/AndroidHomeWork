package com.example.menucontact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<FriendsInfo>{
    //获取资源
    private int resourceId;
    ImageView imageView;


    public ListViewAdapter(MainActivity context, int item_layout, List<FriendsInfo> friendsInfoList) {
        super(context,item_layout,friendsInfoList);
        //保存参数
        resourceId = item_layout;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        FriendsInfo friendsInfo = getItem(position);
        View view;
        if (convertView==null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        } else {
            view = convertView;
        }
        //找到Item布局的控件
        imageView = view.findViewById(R.id.imageView1);
        TextView name = view.findViewById(R.id.text_name);
        TextView message = view.findViewById(R.id.message);
        TextView time = view.findViewById(R.id.time);
        TextView info = view.findViewById(R.id.info);
        CardView cardView = view.findViewById(R.id.cardView);

        imageView.setImageResource(friendsInfo.getImage());
        name.setText(friendsInfo.getName());
        message.setText(friendsInfo.getMessage());
        time.setText(friendsInfo.getTime());
        info.setText(friendsInfo.getInfo()+"");
        if (!info.equals(0)){
            cardView.setVisibility(View.VISIBLE);
        } else {
            cardView.setVisibility(View.INVISIBLE);
        }
        return view;
    }
}
