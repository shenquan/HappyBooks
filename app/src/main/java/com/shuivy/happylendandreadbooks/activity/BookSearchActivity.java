package com.shuivy.happylendandreadbooks.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.shuivy.happylendandreadbooks.R;
import com.shuivy.happylendandreadbooks.adapter.BookListAdapter;
import com.shuivy.happylendandreadbooks.database.MyDataBaseHelper;
import com.shuivy.happylendandreadbooks.models.BookInfo;
import com.shuivy.happylendandreadbooks.util.ListUtility;
import com.shuivy.happylendandreadbooks.util.ToastUtil;

import java.util.ArrayList;

public class BookSearchActivity extends AppCompatActivity {

    private TextView submit;
    private TextView cancel;
    private Context mContext;
    private ArrayList<BookInfo> searchBooks;
    private BookListAdapter searchBookListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_search);
        mContext = getApplicationContext();
        initView();
    }

    public void initView() {
        submit = (TextView) findViewById(R.id.book_search_ok);
        cancel = (TextView) findViewById(R.id.book_search_cancel);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = (EditText)findViewById(R.id.title_book_search_key);
                String value = editText.getText().toString();
                MyDataBaseHelper.searchWord = value;
                ToastUtil.showToast(getApplicationContext(), "查询结果如下~");
                ListView listView = (ListView) findViewById(R.id.list_search_book);
                searchBooks = MyDataBaseHelper.getInstance(mContext).getSearchBooks();
                searchBookListAdapter = new BookListAdapter(mContext, searchBooks);
                listView.setAdapter(searchBookListAdapter);
                ListUtility.setListViewHeightBasedOnChildren(listView);
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
