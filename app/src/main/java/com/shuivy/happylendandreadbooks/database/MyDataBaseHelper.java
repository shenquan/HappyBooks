package com.shuivy.happylendandreadbooks.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by stk on 2016/8/6 0006.
 */
public class MyDataBaseHelper extends SQLiteOpenHelper {
    private Context mContext;
    public static final String CREATE_GUEST = "CREATE TABLE user ("
            + "id integer primary key autoincrement, "
            + "name text)";

    public static final String CREATE_USER = "CREATE TABLE guest ("
            + "id integer primary key autoincrement, "
            + "name text)";

    public static final String CREATE_MESSAGE = "CREATE TABLE message ("
            + "id integer primary key autoincrement, "
            + "type integer,"
            + "guest_code integer,"
            + "guest_name text,"
            + "date text,"
            + "content text)";


    public MyDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER);
        db.execSQL(CREATE_GUEST);
        db.execSQL(CREATE_MESSAGE);

        ContentValues values = new ContentValues();
        values.put("name", "stk_night");
        db.insert("user", null, values);
        values.clear();

        values.put("type", 1);
        values.put("guest_name", "zhoujc");
        values.put("guest_code", "1");
        values.put("content", "求本武侠小说书");
        values.put("date", 1470645607);
        db.insert("message", null, values);
        values.clear();

        values.put("type", 1);
        values.put("guest_name", "zhoujc");
        values.put("guest_code", "1");
        values.put("content", "给你10元");
        values.put("date", 1470645608);
        db.insert("message", null, values);
        values.clear();

        values.put("type", 0);
        values.put("guest_name", "zhoujc");
        values.put("guest_code", "1");
        values.put("content", "5元就行了");
        values.put("date", 1470645609);
        db.insert("message", null, values);
        values.clear();

        values.put("type", 1);
        values.put("guest_name", "zhoujc");
        values.put("guest_code", "1");
        values.put("content", "多谢哈");
        values.put("date", 1470645610);
        db.insert("message", null, values);
        values.clear();

        values.put("type", 1);
        values.put("guest_name", "小智");
        values.put("guest_code", "2");
        values.put("content", "这本书和你换吧");
        values.put("date", 1470545607);
        db.insert("message", null, values);
        values.clear();

        values.put("type", 1);
        values.put("guest_name", "我是小猫咪");
        values.put("guest_code", "3");
        values.put("content", "OK。");
        values.put("date", 1470445607);
        db.insert("message", null, values);
        values.clear();

        values.put("type", 1);
        values.put("guest_name", "我是小书虫");
        values.put("guest_code", "4");
        values.put("content", "哈哈");
        values.put("date", 1470445607);
        db.insert("message", null, values);
        values.clear();

        values.put("type", 1);
        values.put("guest_name", "我是丹丹");
        values.put("guest_code", "5");
        values.put("content", "你的书很不错呀");
        values.put("date", 1470445607);
        db.insert("message", null, values);
        values.clear();

        values.put("type", 1);
        values.put("guest_name", "我是丹丹");
        values.put("guest_code", "5");
        values.put("content", "希望以后多多交流");
        values.put("date", 1470445609);
        db.insert("message", null, values);
        values.clear();

//        ToastUtil.showToast(mContext,"数据库创建成功!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
