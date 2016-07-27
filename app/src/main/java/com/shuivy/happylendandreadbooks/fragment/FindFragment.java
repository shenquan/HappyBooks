package com.shuivy.happylendandreadbooks.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shuivy.happylendandreadbooks.R;

/**
 * Created by stk on 2016/7/22 0022.
 */
public class FindFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find, container, false);
        return view;
    }
}
