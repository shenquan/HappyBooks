package com.shuivy.happylendandreadbooks.models;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 江 on 2016/9/27.
 * modify by zhoujichao on 2016/10/11
 */
public class BookInfo implements Serializable {
    private String title;
    private Bitmap img;
    private String des;
    private String location;
    private String publishType;
    private String bookClass;
    private long createDate;


    public String getBookClass() {
        return bookClass;
    }

    public void setBookClass(String bookClass) {
        this.bookClass = bookClass;
    }

    public String getPublishType() {
        return publishType;
    }

    public void setPublishType(String publishType) {
        this.publishType = publishType;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
