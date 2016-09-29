package com.shuivy.happylendandreadbooks.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shuivy.happylendandreadbooks.R;
import com.shuivy.happylendandreadbooks.models.BookInfo;

import java.util.ArrayList;

/**
 * Created by 江 on 2016/9/27.
 */
public class BookListAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private ArrayList<BookInfo> mData;

    public BookListAdapter(Context context, ArrayList<BookInfo> data) {
        inflater = LayoutInflater.from(context);
        mData = data;
    }

    @Override
    public int getCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mData != null ? mData.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_home_push, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(mData.get(position).getTitle());
        holder.img.setImageBitmap(mData.get(position).getImg());
        holder.des.setText(mData.get(position).getDes());
        holder.location.setText(mData.get(position).getLocation());
        return convertView;
    }

    class ViewHolder {
        TextView title;
        ImageView img;
        TextView des;
        TextView location;

        ViewHolder(View itemView) {
            title = ((TextView) itemView.findViewById(R.id.title));
            img = ((ImageView) itemView.findViewById(R.id.imageView));
            des = ((TextView) itemView.findViewById(R.id.description));
            location = ((TextView) itemView.findViewById(R.id.address));
        }
    }
}
