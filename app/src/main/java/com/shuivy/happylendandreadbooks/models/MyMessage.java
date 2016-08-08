package com.shuivy.happylendandreadbooks.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.shuivy.happylendandreadbooks.database.MyDataBaseHelper;

/**
 * Created by stk on 2016/8/7 0007.
 */
public class MyMessage {
    private Integer id;
    private Integer type;
    private String guestName;
    private String guestCode;
    private String content;
    private Long date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestCode() {
        return guestCode;
    }

    public void setGuestCode(String guestCode) {
        this.guestCode = guestCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Boolean save(Context context){
        MyDataBaseHelper myDataBaseHelper=new MyDataBaseHelper(context,"happy.db",null,6);
        SQLiteDatabase db=myDataBaseHelper.getWritableDatabase();
        db.execSQL("INSERT INTO message " +
                "(guest_name,guest_code,type,content,date)" +
                "VALUES(" +
                "'"+this.getGuestName()+"','"+this.getGuestCode()+"','" +
                this.getType()+"','"+this.getContent()+"','"+this.date+"')");
        return true;
    }
}
