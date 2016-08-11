package com.shuivy.happylendandreadbooks.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/8/11.
 */
public class ToastUtil {
    public static Toast mToast;

    public static void showToast(Context context, String text) {
        if (mToast == null) {
            mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }
}
