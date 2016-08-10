package com.shuivy.happylendandreadbooks.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.shuivy.happylendandreadbooks.R;
import com.shuivy.happylendandreadbooks.activity.LoginActivity;
import com.shuivy.happylendandreadbooks.component.StkMenuLayout;
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
        StkMenuLayout jifen = (StkMenuLayout) mRootView.findViewById(R.id.jifen);
        jifen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "“我的积分”功能待后续完善~", Toast.LENGTH_SHORT).show();
            }
        });
        StkMenuLayout fabu = (StkMenuLayout) mRootView.findViewById(R.id.fabu);
        fabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "“我发布的”功能待后续完善~", Toast.LENGTH_SHORT).show();
            }
        });
        StkMenuLayout jiechu = (StkMenuLayout) mRootView.findViewById(R.id.jiechu);
        jiechu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "“我借出的”功能待后续完善~", Toast.LENGTH_SHORT).show();
            }
        });
        StkMenuLayout jiedao = (StkMenuLayout) mRootView.findViewById(R.id.jiedao);
        jiedao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "“我借到的”功能待后续完善~", Toast.LENGTH_SHORT).show();
            }
        });
        StkMenuLayout zanguo = (StkMenuLayout) mRootView.findViewById(R.id.zanguo);
        zanguo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "“我收藏的”功能待后续完善~", Toast.LENGTH_SHORT).show();
            }
        });
        StkMenuLayout set = (StkMenuLayout) mRootView.findViewById(R.id.set);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "“设置”功能待后续完善~", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.to_login_button:
                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
                break;
        }
    }

}
