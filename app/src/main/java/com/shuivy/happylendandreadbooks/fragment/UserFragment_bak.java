package com.shuivy.happylendandreadbooks.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.shuivy.happylendandreadbooks.R;
import com.shuivy.happylendandreadbooks.adapter.ListMenuAdapter;
import com.shuivy.happylendandreadbooks.viewmodel.ListMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stk on 2016/7/22 0022.
 */
public class UserFragment_bak extends Fragment {

    private List<ListMenu> listMenus = new ArrayList<ListMenu>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        initMenus();
        ListMenuAdapter adapter = new ListMenuAdapter(view.getContext(), R.layout.list_menu, listMenus);
        ListView listView = (ListView) view.findViewById(R.id.user_list_view);
        listView.setAdapter(adapter);
        return view;
    }

    public void initMenus() {
        ListMenu menu1 = new ListMenu("我的书签", R.mipmap.comui_tab_city_selected);
        listMenus.add(menu1);
        ListMenu menu2 = new ListMenu("我发布的", R.mipmap.comui_tab_city_selected);
        listMenus.add(menu2);
        ListMenu menu3 = new ListMenu("我借出的", R.mipmap.comui_tab_city_selected);
        listMenus.add(menu3);
        ListMenu menu4 = new ListMenu("我借到的", R.mipmap.comui_tab_city_selected);
        listMenus.add(menu4);
        ListMenu menu5 = new ListMenu("我赞过的", R.mipmap.comui_tab_city_selected);
        listMenus.add(menu5);
        ListMenu menu6 = new ListMenu("设置", R.mipmap.comui_tab_city_selected);
        listMenus.add(menu6);
    }
}
