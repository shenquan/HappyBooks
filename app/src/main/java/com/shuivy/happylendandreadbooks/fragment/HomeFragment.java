package com.shuivy.happylendandreadbooks.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shuivy.happylendandreadbooks.R;
import com.shuivy.happylendandreadbooks.adapter.MyViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stk on 2016/7/22 0022.
 */
public class HomeFragment extends Fragment {

    private View mRootView;
    private Activity mContext;
    private LayoutInflater mLayoutInflater;
    private int[] mImageIdArray;
    private static final int TIME = 2000;
    private List<View> mLayouts;
    private List<View> mDots;
    private ViewPager mViewPager;
    private int mCount = 3;
    private Handler mHandler = new Handler();
    private int itemPosition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_home, container, false);
            mLayoutInflater = inflater;
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
        mImageIdArray = new int[]{
                R.mipmap.book_viewpager1,
                R.mipmap.book_viewpager2,
                R.mipmap.book_viewpager3,
        };
        initIndex();

//        ListView listView = (ListView) mRootView.findViewById(R.id.listview);
//        DetailInfoAdapter detailInfoAdapter = new DetailInfoAdapter(mContext);
//        listView.setAdapter(detailInfoAdapter);

    }

    private void initIndex() {
        viewPager();//首页viewpager
        mHandler.postDelayed(runnableForViewPager, TIME);//viewpager轮播
        threeBody();//三体点击
    }

    /**
     * 首页上方的viewpager图片展示
     */
    private void viewPager() {
        ViewGroup viewGroup = (ViewGroup) mRootView.findViewById(R.id.viewGroup);
        mViewPager = (ViewPager) mRootView.findViewById(R.id.viewPager);
        //mCount是订单数量，是从订单处得到的数据，我们默认设为3

        mLayouts = new ArrayList<>();
        mDots = new ArrayList<>();
        for (int i = 0; i < mCount; i++) {
            //下面两句必须放在for里面
            View layoutView = mLayoutInflater.inflate(R.layout.viewpager_item, null);
            View dotView = mLayoutInflater.inflate(R.layout.dot, null);

            ImageView imageView = (ImageView) layoutView.findViewById(R.id.viewpager_image);
            imageView.setImageResource(mImageIdArray[i]);
            if (i == 0) {
                dotView.setBackgroundResource(R.drawable.dot_select);
            } else {
                dotView.setBackgroundResource(R.drawable.dot_no_select);
            }
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
            layoutParams.setMargins(20, 0, 20, 0);
            viewGroup.addView(dotView, layoutParams);

            mLayouts.add(layoutView);
            mDots.add(dotView);

        }

        mViewPager.setAdapter(new MyViewPagerAdapter(mLayouts));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < mCount; i++) {
                    if (i == position) {
                        mDots.get(i).setBackgroundResource(R.drawable.dot_select);
                    } else {
                        mDots.get(i).setBackgroundResource(R.drawable.dot_no_select);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * ViewPager的定时器
     */
    Runnable runnableForViewPager = new Runnable() {
        @Override
        public void run() {
            try {
                itemPosition++;
                mHandler.postDelayed(this, TIME);
                mViewPager.setCurrentItem(itemPosition % mCount);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    /**
     * 智能推荐三体点击事件
     */
    private void threeBody() {
        RelativeLayout r_1 = (RelativeLayout) mRootView.findViewById(R.id.r_1);
        RelativeLayout r_2 = (RelativeLayout) mRootView.findViewById(R.id.r_2);
        RelativeLayout r_3 = (RelativeLayout) mRootView.findViewById(R.id.r_3);
        final TextView t_1 = (TextView) mRootView.findViewById(R.id.t_1);
        final TextView t_2 = (TextView) mRootView.findViewById(R.id.t_2);
        final TextView t_3 = (TextView) mRootView.findViewById(R.id.t_3);

        View.OnTouchListener threeBody = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (view.getId()) {
                    case R.id.r_1: {
                        threeBodyOnTouchEvent(motionEvent, t_1);
                        break;
                    }
                    case R.id.r_2: {
                        threeBodyOnTouchEvent(motionEvent, t_2);
                        break;
                    }
                    case R.id.r_3: {
                        threeBodyOnTouchEvent(motionEvent, t_3);
                        break;
                    }

                }
                return true;
            }
        };

        r_1.setOnTouchListener(threeBody);
        r_2.setOnTouchListener(threeBody);
        r_3.setOnTouchListener(threeBody);

    }

    /**
     * 三体触摸动画事件
     */
    private void threeBodyOnTouchEvent(MotionEvent motionEvent, View view) {
        int duration = 150;
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                //按下时缩小
                ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.90f, 1.0f, 0.90f, Animation.RELATIVE_TO_SELF
                        , 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                scaleAnimation.setDuration(duration);
                scaleAnimation.setFillAfter(true);
                view.startAnimation(scaleAnimation);
                break;
            }
            case MotionEvent.ACTION_CANCEL: {
                //移出当前view即取消点击时恢复原样
                ScaleAnimation scaleAnimation = new ScaleAnimation(0.90f, 1.0f, 0.90f, 1.0f, Animation.RELATIVE_TO_SELF
                        , 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                scaleAnimation.setDuration(duration);
                scaleAnimation.setFillAfter(true);
                view.startAnimation(scaleAnimation);
                break;
            }

            case MotionEvent.ACTION_UP: {
                //松开时放大
                ScaleAnimation scaleAnimation = new ScaleAnimation(0.90f, 1.0f, 0.90f, 1.0f, Animation.RELATIVE_TO_SELF
                        , 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                scaleAnimation.setDuration(duration);
                scaleAnimation.setFillAfter(true);
                view.startAnimation(scaleAnimation);
                Toast.makeText(mContext, "后续添加功能", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }
}
