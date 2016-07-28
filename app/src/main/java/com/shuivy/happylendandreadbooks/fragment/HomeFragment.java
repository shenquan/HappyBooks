package com.shuivy.happylendandreadbooks.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shuivy.happylendandreadbooks.R;

/**
 * Created by stk on 2016/7/22 0022.
 */
public class HomeFragment extends Fragment {

    private View mRootView;
    private Activity mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_home, container, false);
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

    }
}
