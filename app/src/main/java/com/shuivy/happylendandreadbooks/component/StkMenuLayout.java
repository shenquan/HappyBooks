package com.shuivy.happylendandreadbooks.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shuivy.happylendandreadbooks.R;

/**
 * Created by stk on 2016/7/22 0022.
 */
public class StkMenuLayout extends LinearLayout {
    private String mText;

    public StkMenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.list_menu, this);
        TextView menuText = (TextView) findViewById(R.id.menu_text);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.StkMenuLayout,
                0, 0);
        try {
            menuText.setText(a.getText(R.styleable.StkMenuLayout_Text));
        } finally {
            a.recycle();
        }


    }
}
