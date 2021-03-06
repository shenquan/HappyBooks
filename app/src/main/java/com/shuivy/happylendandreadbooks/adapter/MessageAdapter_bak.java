package com.shuivy.happylendandreadbooks.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shuivy.happylendandreadbooks.R;

/**
 * Created by sqhan on 2016/7/28.
 */
public class MessageAdapter_bak extends BaseAdapter {
    private Context mContext;
    public static final int COUNT = 10;
    private int[] headImage = new int[]{
            R.mipmap.share_person_1,
            R.mipmap.share_person_2,
            R.mipmap.share_person_3,
            R.mipmap.share_person_4,
            R.mipmap.share_person_5,
            R.mipmap.share_person_6,
            R.mipmap.share_person_7,
            R.mipmap.share_person_8,
            R.mipmap.share_person_9,
            R.mipmap.share_person_10,
    };
    private String[] nickName = new String[]{
            "爱书宝贝",
            "小小书虫",
            "读书如此简单",
            "Hello Kitty",
            "启动追一个梦",
            "我是小猫咪",
            "随风飞舞~",
            "白马王子",
            "校园时代",
            "暗夜小精灵",
    };
    private String[] signature = new String[]{
            "上海大学见面哦，见面聊~",
            "你的书挺好看的，以后多多交流",
            "很高兴见到你",
            "^_^",
            "你的书能便宜点吗",
            "呀。",
            "你好呀，朋友",
            "hi，你好呀，我们的书换着看怎么样",
            "书读百遍其义自见",
            "哈哈",
    };

    public MessageAdapter_bak(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.message_item, null);
            holder.imageView = (ImageView) convertView.findViewById(R.id.list_item_head_image);
            holder.nickNameTextView = (TextView) convertView.findViewById(R.id.list_item_nick_name);
            holder.latestChatTextView = (TextView) convertView.findViewById(R.id.list_item_latest_chat);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //// TODO: 2016/7/28
        //此处将会从服务器请求到数据，动态更改listview
        holder.imageView.setImageResource(headImage[i]);
        holder.nickNameTextView.setText(nickName[i]);
        holder.latestChatTextView.setText(signature[i]);
        /*if (i == 1 || i == 3 || i == 8) {
            holder.nickNameTextView.setTextColor(0xffdd0000);
            holder.latestChatTextView.setTextColor(0xffdd0000);
        } else {//此处必须加else，因为会重用
            holder.nickNameTextView.setTextColor(0xff222222);
            holder.latestChatTextView.setTextColor(0xff666666);
        }*/

        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
        TextView nickNameTextView;
        TextView latestChatTextView;
    }

}
