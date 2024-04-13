package com.example.listview_test;

public class FriendsInfo {
    private int imageId;        //头像
    private String name;        //名字
    private String message;     //消息
    private String time;        //时间
    private int info;           //显示未读消息个数

    public FriendsInfo(int imageId, String name, String message, String time, int info) {
        this.imageId = imageId;
        this.name = name;
        this.message = message;
        this.time = time;
        this.info = info;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }
}