package com.shuivy.happylendandreadbooks.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shuivy.happylendandreadbooks.R;
import com.shuivy.happylendandreadbooks.models.BookInfo;
import com.shuivy.happylendandreadbooks.util.ToastUtil;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by 江 on 2016/9/27.
 * modify by zhoujichao 2016/10/11
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
        //计算距离发布的时间
        String dateStr = null;
        long curDate = new Date().getTime();
        long createDate = mData.get(position).getCreateDate();
        int secs = (int)(curDate-createDate)/1000;
        int mins = secs/60;
        int hours = mins/60;
        int days = hours/24;
        if(days>=1){
            dateStr = days + "天前发布";
        }else if(hours>=1){
            dateStr = hours + "小时前发布";
        }else{
            dateStr = mins + "分钟前发布";
        }
        holder.title.setText(mData.get(position).getTitle());
        holder.img.setImageBitmap(mData.get(position).getImg());
        holder.time.setText(dateStr);
        holder.location.setText(mData.get(position).getLocation());
        holder.publishType.setText(mData.get(position).getPublishType());
        return convertView;
    }

    class ViewHolder {
        TextView title;
        ImageView img;
        TextView time;
        TextView location;
        TextView publishType;


        ViewHolder(View itemView) {
            title = ((TextView) itemView.findViewById(R.id.title));
            img = ((ImageView) itemView.findViewById(R.id.imageView));
            time = ((TextView) itemView.findViewById(R.id.publish_time));
            location = ((TextView) itemView.findViewById(R.id.address));
            publishType = ((TextView) itemView.findViewById(R.id.publish_type));
        }
    }
}
