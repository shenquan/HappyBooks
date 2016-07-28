package com.shuivy.happylendandreadbooks.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shuivy.happylendandreadbooks.R;
import com.shuivy.happylendandreadbooks.activity.LoginActivity;
import com.shuivy.happylendandreadbooks.viewmodel.ListMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stk on 2016/7/22 0022.
 */
public class UserFragment extends Fragment implements View.OnClickListener {

    private Button logOutButton;
    private List<ListMenu> listMenus = new ArrayList<ListMenu>();
    private View mRootView;
    private Activity mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_user, container, false);
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
        logOutButton = (Button) mRootView.findViewById(R.id.to_login_button);
        logOutButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.to_login_button:
                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
        }
    }

}
