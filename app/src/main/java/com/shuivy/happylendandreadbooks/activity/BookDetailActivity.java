package com.shuivy.happylendandreadbooks.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.shuivy.happylendandreadbooks.R;
import com.shuivy.happylendandreadbooks.database.MyDataBaseHelper;
import com.shuivy.happylendandreadbooks.models.BookInfo;
import com.shuivy.happylendandreadbooks.models.BookInfoS;
import com.shuivy.happylendandreadbooks.util.ToastUtil;

import java.util.Date;

public class BookDetailActivity extends AppCompatActivity {
    private TextView title;
    private TextView des;
    private TextView type;
    private TextView time;
    private ImageView img;
    private Button sub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        initView();
    }

    public void initView(){
        img = (ImageView)findViewById(R.id.detail_imageView);
        title = (TextView) findViewById(R.id.detail_title);
        des = (TextView) findViewById(R.id.detail_description);
        type = (TextView) findViewById(R.id.detail_type);
        time = (TextView) findViewById(R.id.detail_time);
        sub = (Button)findViewById(R.id.detail_submit);
        final Intent intent = getIntent();
        String titleStr = intent.getStringExtra("title");
      //  BookInfoS book = (BookInfoS) intent.getSerializableExtra("book");
        BookInfo book = MyDataBaseHelper.getInstance(this).getBook(titleStr);
        String dateStr;
        long createDate = book.getCreateDate();
        long curDate = new Date().getTime();
        int secs = (int)(curDate-createDate)/1000;
        int mins = secs/60;
        int hours = mins/60;
        int days = hours/24;
        if(days>=1){
            dateStr = days + "天前发布";
        }else if(hours>=1){
            dateStr = hours + "小时前发布";
        }else{
            dateStr = mins + "分钟前发布";
        }
        title.setText(book.getTitle());
        des.setText(book.getDes());
        type.setText(book.getPublishType());
        time.setText(dateStr);
        img.setImageBitmap(book.getImg());

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.putExtra("status",100);
                setResult(RESULT_OK,intent);
                ToastUtil.showToast(getApplicationContext(),"已经通知该书友~");
                finish();
            }
        });
//        ToastUtil.showToast(getApplicationContext(),book.getTitle());
    }
}
