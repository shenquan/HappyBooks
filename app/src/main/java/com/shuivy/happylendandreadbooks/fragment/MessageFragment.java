package com.shuivy.happylendandreadbooks.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.shuivy.happylendandreadbooks.R;
import com.shuivy.happylendandreadbooks.adapter.MessageAdapter;

/**
 * Created by stk on 2016/7/22 0022.
 */
public class MessageFragment extends Fragment {
    private View mRootView;
    private Activity mContext;

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
        ListView message_list = (ListView) mRootView.findViewById(R.id.message_list);
        MessageAdapter messageAdapter = new MessageAdapter(mContext);
        message_list.setAdapter(messageAdapter);

    }

}
