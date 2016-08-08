package com.shuivy.happylendandreadbooks.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.shuivy.happylendandreadbooks.R;
import com.shuivy.happylendandreadbooks.activity.ChatActivity;
import com.shuivy.happylendandreadbooks.adapter.MessageAdapter;
import com.shuivy.happylendandreadbooks.models.MyMessage;
import com.shuivy.happylendandreadbooks.util.MessageListBuilder;

import java.util.List;

/**
 * Created by stk on 2016/7/22 0022.
 */
public class MessageFragment extends Fragment {
    private View mRootView;
    private Activity mContext;
    private List<MyMessage> messList;
    MessageListBuilder messageListBuilder;
    ListView messageListView;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_message, container, false);
            initView();
        } else {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (parent != null) {
                parent.removeView(mRootView);
            }
        }

        return mRootView;
    }

    private void initView() {
        messageListBuilder=new MessageListBuilder(mContext);
        messList= messageListBuilder.getMessages();
        messageListView = (ListView) mRootView.findViewById(R.id.message_list);

        final MessageAdapter messageAdapter = new MessageAdapter(mContext);
        messageListView.setAdapter(messageAdapter);
        messageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(mContext,ChatActivity.class);
                intent.putExtra("guestName",messList.get(position).getGuestName());
                intent.putExtra("guestCode",messList.get(position).getGuestCode());
                startActivity(intent);

            }
        });
    }



}
