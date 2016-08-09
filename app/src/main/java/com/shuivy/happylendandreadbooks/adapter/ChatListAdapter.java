package com.shuivy.happylendandreadbooks.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.shuivy.happylendandreadbooks.R;
import com.shuivy.happylendandreadbooks.models.MyMessage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;


/**
 * Created by madhur on 17/01/15.
 */
public class ChatListAdapter extends BaseAdapter {

    private ArrayList<MyMessage> chatMessages;
    private Context mContext;
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("HH:mm");

    public ChatListAdapter(ArrayList<MyMessage> chatMessages, Context context) {
        this.chatMessages = chatMessages;
        this.mContext = context;

    }


    @Override
    public int getCount() {
        return chatMessages.size();
    }

    @Override
    public MyMessage getItem(int position) {
        return chatMessages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = null;
        MyMessage message = getItem(position);
        ViewHolder1 holder1;
        ViewHolder2 holder2;

        if (message.getType() == 0) {
            if (convertView == null) {
                v = LayoutInflater.from(mContext).inflate(R.layout.item_chat_to, null, false);
                holder1 = new ViewHolder1();

                holder1.messageTextView = (TextView) v.findViewById(R.id.message_text);
                holder1.timeTextView = (TextView) v.findViewById(R.id.time_text);

                v.setTag(holder1);
            } else {
                v = convertView;
                holder1 = (ViewHolder1) v.getTag();

            }

//            holder1.messageTextView.setText(Emoji.replaceEmoji(message.getMessageText(), holder1.messageTextView.getPaint().getFontMetricsInt(), AndroidUtilities.dp(16)));
//            holder1.timeTextView.setText(SIMPLE_DATE_FORMAT.format(message.getMessageTime()));
            holder1.messageTextView.setText(message.getContent());
            holder1.timeTextView.setText(SIMPLE_DATE_FORMAT.format(message.getDate()));

        } else if (message.getType() == 1) {

            if (convertView == null) {
                v = LayoutInflater.from(mContext).inflate(R.layout.item_chat_from, null, false);

                holder2 = new ViewHolder2();

                holder2.messageTextView = (TextView) v.findViewById(R.id.message_text);
                holder2.timeTextView = (TextView) v.findViewById(R.id.time_text);
                holder2.nameTextView = (TextView) v.findViewById(R.id.chat_guest_name);

                v.setTag(holder2);

            } else {
                v = convertView;
                holder2 = (ViewHolder2) v.getTag();

            }

            holder2.messageTextView.setText(message.getContent());
            holder2.timeTextView.setText(SIMPLE_DATE_FORMAT.format(message.getDate()));
            holder2.nameTextView.setText(message.getGuestName());
        }
        return v;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        MyMessage message = getItem(position);
        return message.getType();
    }

    private class ViewHolder1 {
        public TextView messageTextView;
        public TextView timeTextView;


    }

    private class ViewHolder2 {
        public TextView messageTextView;
        public TextView timeTextView;
        public TextView nameTextView;
    }
}
