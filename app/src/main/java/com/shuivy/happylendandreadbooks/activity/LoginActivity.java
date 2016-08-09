package com.shuivy.happylendandreadbooks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shuivy.happylendandreadbooks.R;

/**
 * Created by stk on 2016/7/24 0024.
 */
public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.login);

        Button niming = (Button) findViewById(R.id.niming);
        niming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, BookMainActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText userNameET = (EditText) findViewById(R.id.user_name);
                EditText passwordET = (EditText) findViewById(R.id.password);
                String userName = userNameET.getText().toString();
                String password = passwordET.getText().toString();
                if ("admin".equals(userName) && "123".equals(password)) {
                    Intent intent = new Intent(LoginActivity.this, BookMainActivity.class);
                    startActivity(intent);
                    LoginActivity.this.finish();
                } else {
                    Toast.makeText(LoginActivity.this, "账户名或密码错误", Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}
