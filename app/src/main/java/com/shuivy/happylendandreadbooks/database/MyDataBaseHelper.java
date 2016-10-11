package com.shuivy.happylendandreadbooks.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.shuivy.happylendandreadbooks.models.BookInfo;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by stk on 2016/8/6 0006.
 */
public class MyDataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "happy.db";
    private static final int DATABASE_VERSION = 2;
    private static MyDataBaseHelper sInstance;
    private SQLiteDatabase db;

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
    public static final String CREATE_BOOK = "CREATE TABLE book ("
            + " _id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + " title text,"
            + " img BLOB,"
            + " des text,"
            + " create_date integer ,"
            + " location text)";

    public static MyDataBaseHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new MyDataBaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    public MyDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("TAG", "onCreate: ..................................................................");
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

        db.execSQL(CREATE_BOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("TAG", "onUpgrade: .................................................................." + newVersion);
    }

    public ArrayList<BookInfo> getAllBooks() {
        ArrayList<BookInfo> mlist = new ArrayList<BookInfo>();
        BookInfo result = null;

        Cursor c = db.query("book", new String[]{"_id", "title", "img", "des", "location","create_date"}, null, null, null, null, "_id desc");
        while (c.moveToNext()) {
            result = new BookInfo();
            result.setTitle(c.getString(1));
            byte[] blob = c.getBlob(2);
            result.setImg(BitmapFactory.decodeByteArray(blob, 0, blob.length));
            result.setDes(c.getString(3));
            result.setLocation(c.getString(4));
            result.setCreateDate(c.getLong(5));
            mlist.add(result);
        }
        c.close();
        Log.d("TAG", "mlist.size: .................................................................." + mlist.size());
        return mlist;
    }

    public void storeUrineTestData(BookInfo data) {
        ContentValues cv = new ContentValues();
        cv.put("title", data.getTitle());
        cv.put("img", Bitmap2Bytes(data.getImg()));
        cv.put("des", data.getDes());
        cv.put("location", data.getLocation());
        cv.put("create_date",data.getCreateDate());

        db.insert("book", null, cv);
    }

    public byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
}
