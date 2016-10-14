package com.shuivy.happylendandreadbooks.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.shuivy.happylendandreadbooks.R;
import com.shuivy.happylendandreadbooks.util.ToastUtil;

public class BookSearchActivity extends AppCompatActivity {

    private TextView submit;
    private TextView cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_search);
        initView();
    }

    public void initView(){
        submit = (TextView)findViewById(R.id.book_search_ok);
        cancel = (TextView)findViewById(R.id.book_search_cancel);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showToast(getApplicationContext(),"提交成功~");
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
