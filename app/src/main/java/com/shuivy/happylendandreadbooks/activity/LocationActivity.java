package com.shuivy.happylendandreadbooks.activity;


import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.offline.MKOLSearchRecord;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.shuivy.happylendandreadbooks.R;
import com.shuivy.happylendandreadbooks.util.ToastUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LocationActivity extends AppCompatActivity{
    private EditText searchKey;
    private ListView mapResults;
    private TextView cancel;
    private TextView submit;
    private SuggestionSearch search;


    private String key = "上海大学";
    private String city = "上海";
    private List<String> results;

    private boolean isSelected = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        initView();
    }

    public void initView(){
        Intent o = getIntent();
        city = o.getStringExtra("city");
        key = o.getStringExtra("key");


        results = new ArrayList<String>();
        mapResults = (ListView) findViewById(R.id.map_results);
        cancel = (TextView) findViewById(R.id.map_search_cancel);
        submit = (TextView) findViewById(R.id.map_search_ok);
        searchKey = (EditText) findViewById(R.id.title_map_search_key);
        searchKey.setText(key);
        searchKey.setSelection(key.length());

        SDKInitializer.initialize(getApplicationContext()); //初始化百度地图SDK

        //相关地点查找结果监听器
        OnGetSuggestionResultListener listener = new OnGetSuggestionResultListener() {
            public void onGetSuggestionResult(SuggestionResult res) {
                results.clear();
                if (res == null || res.getAllSuggestions() == null) { //未找到相关结果
                    return;
                }else
                {
                    List<SuggestionResult.SuggestionInfo> resl=res.getAllSuggestions();
                    for(int i=0;i<resl.size();i++)
                    {
                        results.add(resl.get(i).key);
                    }
                    mapResults.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, results));
                }
                //获取在线建议检索结果
            }
        };
        search = SuggestionSearch.newInstance();
        search.setOnGetSuggestionResultListener(listener);
        search.requestSuggestion((new SuggestionSearchOption())
                .keyword(key)
                .city(city));

        //搜索文本改变事件
        searchKey.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(isSelected){
                    isSelected = false;
                    mapResults.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, new ArrayList<String>()));
                    return;
                }
                String tempKey = searchKey.getText().toString();
                search.requestSuggestion((new SuggestionSearchOption())
                        .keyword(tempKey)
                        .city(city));
            }
        });

        //取消按钮点击事件
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //确定按钮点击事件
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("selectLocation", searchKey.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        mapResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                isSelected = true;
                String tempKey = results.get(i);
                searchKey.setText(tempKey);

            }
        });


    }


}


