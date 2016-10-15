package com.shuivy.happylendandreadbooks.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.Point;
import com.shuivy.happylendandreadbooks.R;
import com.shuivy.happylendandreadbooks.activity.BookDetailActivity;
import com.shuivy.happylendandreadbooks.database.MyDataBaseHelper;
import com.shuivy.happylendandreadbooks.models.BookInfo;
import com.shuivy.happylendandreadbooks.models.BookInfoS;
import com.shuivy.happylendandreadbooks.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by stk on 2016/7/22 0022.
 */
public class MarketFragment extends Fragment {

    private View mRootView;
    private ListView listView1;
    private LinearLayout info_need;
    private Activity mContext;

    // 定位相关
    LocationClient mLocClient;
    public MyLocationListener myListener = new MyLocationListener();
    private MyLocationConfiguration.LocationMode mCurrentMode;
    private List<BookInfo> allBooks;
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private OverlayOptions overlayOptions;


    boolean isFirstLoc = true; // 是否首次定位

    private BitmapDescriptor mCurrentMarker; //自定义覆盖物
    private Marker marker;
    private LatLng curPos;
    private InfoWindow mInfoWindow;

    private BookInfo seleted;
    private static final int DETAILREQUEST = 1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        SDKInitializer.initialize(mContext.getApplicationContext());

        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_market, container, false);

        } else {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (parent != null) {
                parent.removeView(mRootView);
            }
        }
        allBooks = MyDataBaseHelper.getInstance(getActivity()).getAllBooks();
        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;

        // 地图初始化
        mMapView = (MapView) mRootView.findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);

        mCurrentMarker = BitmapDescriptorFactory
                .fromResource(R.mipmap.smile_2);
        mBaiduMap
                .setMyLocationConfigeration(new MyLocationConfiguration(
                        mCurrentMode, true, mCurrentMarker));
        // 定位初始化
        mLocClient = new LocationClient(mContext.getApplicationContext());
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
//      option.setScanSpan(1000);
        mLocClient.setLocOption(option);
        mLocClient.start();



       /* mCurrentMarker = BitmapDescriptorFactory
                .fromResource(R.mipmap.book_maker);

        mBaiduMap
                .setMyLocationConfigeration(new MyLocationConfiguration(
                        mCurrentMode, true, mCurrentMarker));*/
        mBaiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChange(MapStatus mapStatus) {

            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {
                mBaiduMap.clear();
                getNearbyPublish();
            }
        });

        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(final Marker marker) {
                BookInfo book = (BookInfo) marker.getExtraInfo().get("book");

                View view = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.book_info_window,null);

                TextView bookName = (TextView) view.findViewById(R.id.window_book_name);
                TextView bookDesc = (TextView) view.findViewById(R.id.window_book_desc);
                TextView bookType = (TextView) view.findViewById(R.id.window_book_type);
                String t=book.getTitle(),d=book.getDes();
                if(t.length()>10){
                    t = t.substring(0,10)+"...";
                }
                if(d.length()>30){
                    d = d.substring(0,30)+"...";
                }
                bookName.setText(book.getTitle());
                bookDesc.setText(book.getDes());
                bookType.setText(book.getPublishType());
                seleted = book;


                /*LinearLayout window = new LinearLayout(getActivity());

                window.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                window.setOrientation(LinearLayout.VERTICAL);

                LinearLayout.LayoutParams txtParams = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                TextView bookTitle = new TextView(getActivity());
                //bookTitle.setBackgroundResource(R.mipmap.title_search_blank);
                bookTitle.setPadding(30, 20, 30, 50);
                bookTitle.setText(book.getTitle());
                bookTitle.setTextColor(Color.GRAY);
                bookTitle.setBackgroundColor(Color.WHITE);
                window.addView(bookTitle);

                TextView bookDesc = new TextView(getActivity());
                //bookTitle.setBackgroundResource(R.mipmap.title_search_blank);
                bookDesc.setPadding(30, 20, 30, 50);
                bookDesc.setText(book.getDes());
                bookDesc.setTextColor(Color.GRAY);
                bookDesc.setBackgroundColor(Color.WHITE);
                window.addView(bookDesc);

                Button button = new Button(getActivity());
                button.setText("查看");
                window.addView(button);*/

                InfoWindow.OnInfoWindowClickListener listener = new InfoWindow.OnInfoWindowClickListener() {
                    public void onInfoWindowClick() {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(),BookDetailActivity.class);
                    intent.putExtra("title",seleted.getTitle());
                    startActivityForResult(intent,DETAILREQUEST);
                    mBaiduMap.hideInfoWindow();
                    }
                };
                LatLng ll = marker.getPosition();

                mInfoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(view), ll, -47, listener);
                mBaiduMap.showInfoWindow(mInfoWindow);
                return true;
            }
        });

        return mRootView;
    }


    private class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(0).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);

            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(),
                        location.getLongitude());
                curPos = ll;
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(17.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }

    public void getNearbyPublish(){
        mCurrentMarker = BitmapDescriptorFactory
                .fromResource(R.mipmap.book_maker);

        for (BookInfo book:allBooks) {

            LatLng l = new LatLng(book.getLatitude(),book.getLongitude());
            if(mBaiduMap.getMapStatus().bound.contains(l)) {
                overlayOptions = new MarkerOptions().position(l).icon(mCurrentMarker);
                marker=(Marker) (mBaiduMap.addOverlay(overlayOptions));
                Bundle bundle = new Bundle();
                bundle.putSerializable("book",book);
                marker.setExtraInfo(bundle);
            }
        }


    }




    @Override
    public void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        // 退出时销毁定位
        mLocClient.stop();
        // 关闭定位图层
        mBaiduMap.setMyLocationEnabled(false);
        mMapView.onDestroy();
        mMapView = null;
        super.onDestroy();
    }

}
