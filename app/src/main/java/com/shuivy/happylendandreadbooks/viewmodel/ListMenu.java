package com.shuivy.happylendandreadbooks.viewmodel;

/**
 * Created by stk on 2016/7/22 0022.
 */
public class ListMenu {
    private String text;
    private int leftIconId;

    public ListMenu(String text, int leftIconId) {
        this.text = text;
        this.leftIconId = leftIconId;
    }

    public String getText() {
        return text;
    }

    public int getLeftIconId() {
        return leftIconId;
    }
}
