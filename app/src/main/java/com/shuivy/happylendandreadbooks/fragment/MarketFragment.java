package com.shuivy.happylendandreadbooks.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.shuivy.happylendandreadbooks.R;

/**
 * Created by stk on 2016/7/22 0022.
 */
public class MarketFragment extends Fragment {

    private View mRootView;
    private Activity mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_market, container, false);
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
        RelativeLayout r_1 = (RelativeLayout) mRootView.findViewById(R.id.r_1);
        RelativeLayout r_2 = (RelativeLayout) mRootView.findViewById(R.id.r_2);
        final View view_1 = mRootView.findViewById(R.id.view_1);
        final View view_2 = mRootView.findViewById(R.id.view_2);
        view_1.setVisibility(View.VISIBLE);
        view_2.setVisibility(View.GONE);

        r_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view_1.setVisibility(View.VISIBLE);
                view_2.setVisibility(View.GONE);
            }
        });
        r_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view_1.setVisibility(View.GONE);
                view_2.setVisibility(View.VISIBLE);
            }
        });
    }
}
