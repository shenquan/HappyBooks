package com.shuivy.happylendandreadbooks.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shuivy.happylendandreadbooks.R;
import com.shuivy.happylendandreadbooks.viewmodel.ListMenu;

import java.util.List;

/**
 * Created by stk on 2016/7/22 0022.
 */
public class ListMenuAdapter extends ArrayAdapter<ListMenu> {
    private int resourceId;

    public ListMenuAdapter(Context context, int textViewResourceId, List<ListMenu> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListMenu listMenu = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        ImageView leftIcon = (ImageView) view.findViewById(R.id.left_icon);
        TextView menuText = (TextView) view.findViewById(R.id.menu_text);
        leftIcon.setImageResource(listMenu.getLeftIconId());
        menuText.setText(listMenu.getText());
        return view;
    }
}
