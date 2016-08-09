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
 * Created by HSQ on 2016/8/9.
 */
public class DetailInfoAdapter extends BaseAdapter {
    private Context mContext;
    private static final int COUNT = 3;
    private int[] image = new int[]{
            R.mipmap.book_detail_1,
            R.mipmap.book_detail_2,
            R.mipmap.book_detail_3,
    };
    private String[] title = new String[]{
            "《拥抱》一书换《简爱》一书",
            "《流星之盼》换一本散文书",
            "求购一本《镜中蔷薇》一书"
    };
    private String[] description = new String[]{
            "这一本书是从网上刚买没多久的，内容很不错哦",
            "日本当代长篇小说，东野圭吾情感悬疑大作，日本连续56天平均每15秒售出1本，破东野圭吾小说销售纪录”",
            "求购一本镜中蔷薇一书，新旧无所谓的哦"
    };
    private String[] address = new String[]{
            "地址：上海大学宝山校区",
            "地址：上海大学宝山校区计算机大楼",
            "地址：上海大学宝山校区文学院"
    };

    public DetailInfoAdapter(Context context) {
        mContext = context;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.detail_info_item, null);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            holder.description = (TextView) convertView.findViewById(R.id.description);
            holder.address = (TextView) convertView.findViewById(R.id.address);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(title[i]);
        holder.imageView.setImageResource(image[i]);
        holder.description.setText(description[i]);
        holder.address.setText(address[i]);

        return convertView;
    }

    class ViewHolder {
        TextView title;
        ImageView imageView;
        TextView description;
        TextView address;
    }
}
