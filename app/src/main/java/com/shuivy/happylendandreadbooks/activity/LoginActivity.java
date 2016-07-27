package com.shuivy.happylendandreadbooks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.shuivy.happylendandreadbooks.R;

/**
 * Created by stk on 2016/7/24 0024.
 */
public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button niming = (Button)findViewById(R.id.niming);
        niming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,BookMainActivity.class);
                startActivity(intent);
            }
        });
    }
}
