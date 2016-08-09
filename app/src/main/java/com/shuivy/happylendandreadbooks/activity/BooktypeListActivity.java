package com.shuivy.happylendandreadbooks.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.shuivy.happylendandreadbooks.R;

import java.util.ArrayList;
import java.util.List;

public class BooktypeListActivity extends Activity {
    List<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("选择分类");
        setContentView(R.layout.activity_booktype_list);
        ListView bookType = (ListView) findViewById(R.id.bookType);
        bookType.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getData()));
        bookType.setCacheColorHint(Color.TRANSPARENT);
        bookType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String typeName = data.get(position);
                Intent intent = new Intent();
                intent.putExtra("typeName", typeName);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private List<String> getData() {

        data = new ArrayList<String>();
        data.add("文学");
        data.add("小说");
        data.add("少儿");
        data.add("科普读物");
        data.add("动漫/幽默");
        data.add("社会科学");
        data.add("历史");
        data.add("文化");
        data.add("艺术");
        data.add("收藏");
        data.add("地图");
        data.add("家庭教育");
        data.add("旅游");
        data.add("美丽装扮");
        data.add("两性关系");
        data.add("工具书");
        data.add("经济");
        return data;
    }
}
