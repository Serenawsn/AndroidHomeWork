package com.example.menucontact;

public class FriendsInfo {
    private Integer Image;   //头像
    private String name;    //名字
    private String message; //消息
    private String time;    //时间
    private Integer info;   //消息个数

    public FriendsInfo(Integer image, String name, String message, String time, Integer info) {
        Image = image;
        this.name = name;
        this.message = message;
        this.time = time;
        this.info = info;
    }

    public Integer getImage() {
        return Image;
    }

    public void setImage(Integer image) {
        Image = image;
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

    public Integer getInfo() {
        return info;
    }

    public void setInfo(Integer info) {
        this.info = info;
    }
}
