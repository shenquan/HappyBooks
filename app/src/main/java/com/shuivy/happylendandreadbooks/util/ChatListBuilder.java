package com.shuivy.happylendandreadbooks.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.shuivy.happylendandreadbooks.database.MyDataBaseHelper;
import com.shuivy.happylendandreadbooks.models.MyMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stk on 2016/8/7 0007.
 */
public class ChatListBuilder {
    private Context mContext;
    public ChatListBuilder(Context context){
        this.mContext=context;
    }

    List<MyMessage> messList=new ArrayList<>();

    public List<MyMessage> getMessages(String guestCode){
        this.messList=getMessagesFromSQLite(guestCode);
        return this.messList;
    }

    public List<MyMessage> getMessagesFromSQLite(String guestCode){
        MyDataBaseHelper dataBaseHelper=new MyDataBaseHelper(mContext,"happy.db",null,6);
        SQLiteDatabase db=dataBaseHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM message WHERE guest_code='"+guestCode+"'",null);
        if(cursor.moveToFirst()){
            do{
                MyMessage message=new MyMessage();
                message.setGuestCode(cursor.getString(cursor.getColumnIndex("guest_code")));
                message.setGuestName(cursor.getString(cursor.getColumnIndex("guest_name")));
                message.setType(cursor.getInt(cursor.getColumnIndex("type")));
                message.setContent(cursor.getString(cursor.getColumnIndex("content")));
                message.setDate(cursor.getLong(cursor.getColumnIndex("date")));
                this.messList.add(message);
            }while(cursor.moveToNext());
            cursor.close();
        }
        return this.messList;



    }

}
