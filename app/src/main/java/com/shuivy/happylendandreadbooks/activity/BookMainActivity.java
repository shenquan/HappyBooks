package com.shuivy.happylendandreadbooks.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.shuivy.happylendandreadbooks.R;

/**
 * Created by Hanshenquan on 2016/7/2.
 */
public class BookMainActivity extends AppCompatActivity {
    private static long EXIT_TIME = 0;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_main);

        // TODO: 2016/7/2 添加代码
        initView();//初始化界面

    }

    /**
     * 初始化界面
     */
    private void initView() {
        // TODO: 2016/7/2 添加代码
        mContext = getApplicationContext();

    }

    /**
     * 点击退出按钮
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode) {
            //清除动画
            getWindow().getDecorView().clearAnimation();
            if ((System.currentTimeMillis() - EXIT_TIME) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序~", Toast.LENGTH_SHORT).show();
                EXIT_TIME = System.currentTimeMillis();
            } else {
                BookMainActivity.this.finish();
            }
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
}
