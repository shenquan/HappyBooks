package com.shuivy.happylendandreadbooks.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.shuivy.happylendandreadbooks.R;
import com.shuivy.happylendandreadbooks.database.MyDataBaseHelper;
import com.shuivy.happylendandreadbooks.models.BookInfo;
import com.shuivy.happylendandreadbooks.util.ToastUtil;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.util.Date;

/**
 * Created by sqhan on 2016/7/27.
 * modify by zhoujichao on 2016/10/11
 */
public class PublishActivity extends Activity {
    private ImageButton imageAddButton;
    private ImageButton imageDeleteButton;
    private ImageView imageView;
    public static final String IMAGE_UNSPECIFIED = "image/*";
    public static final int NONE = 0;
    public static final int BOOKTYPEREQUEST = 1;
    public static final int PHOTOZOOM = 2; // 图片缩放
    public static final int PHOTORESOULT = 3;// 传回的结果
    public static final int LOCATIONREQUEST = 4; //跳转定位页面

    private EditText title;
    private EditText des;
    private TextView location;
    private RadioGroup publishType;
    private RadioButton seletedType;
    private TextView typeName;
    private TextView textMapLocation;

    //判断是否定位完成
    private boolean isLocated = false;

    //定位相关
    private MyLocationListener myListener = new MyLocationListener();
    private LocationClient mLocClient;
    private String city;
    private double latitude; //当前用户纬度
    private double longitude;//当前用户经度
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publish);
        initIndex();
    }

    private void initView() {
        title = (EditText) findViewById(R.id.et_title);
        des = (EditText) findViewById(R.id.et_content_text);
        location = (TextView) findViewById(R.id.publish_location);
        imageAddButton = (ImageButton) findViewById(R.id.add_image);
        imageDeleteButton = (ImageButton) findViewById(R.id.delete_image);
        imageView = (ImageView) findViewById(R.id.imageViewId);
        publishType = (RadioGroup) findViewById(R.id.publish_type);
        typeName = (TextView) findViewById(R.id.typeName);
        textMapLocation = (TextView) findViewById(R.id.text_map_location);

        //获取定位
        mLocClient = new LocationClient(getApplicationContext());
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setOpenGps(true);    // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        option.setIsNeedAddress(true);
        option.setIsNeedLocationDescribe(true);
        mLocClient.setLocOption(option);
        mLocClient.start();


        RelativeLayout typeSelector = (RelativeLayout) findViewById(R.id.typeSelector);
        typeSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PublishActivity.this, BooktypeListActivity.class);
                startActivityForResult(intent, BOOKTYPEREQUEST);
            }
        });

        //添加图片按钮点击事件
        imageAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_UNSPECIFIED);
                startActivityForResult(intent, PHOTOZOOM);
            }
        });

        //删除图片按钮点击事件
        imageDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setBackgroundResource(0);
                imageView.setVisibility(View.GONE);
                imageDeleteButton.setVisibility(View.GONE);
            }
        });


        //选择地址打开activity
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isLocated){
                    ToastUtil.showToast(getApplicationContext(), "请等待定位成功~");
                    return;
                }
                Intent intent = new Intent();
                intent.setClass(PublishActivity.this,LocationActivity.class);
                intent.putExtra("key",location.getText());
                intent.putExtra("city",city);
                startActivityForResult(intent,LOCATIONREQUEST);
            }
        });

        Button publish = (Button) findViewById(R.id.publish);
        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isLocated){
                    ToastUtil.showToast(getApplicationContext(), "请等待定位成功~");
                    return;
                }
                Intent intent = new Intent(PublishActivity.this, BookMainActivity.class);
                startActivity(intent);
                PublishActivity.this.finish();
                ToastUtil.showToast(getApplicationContext(), "发布成功~");
                storeToDb();
            }
        });

    }

    private void storeToDb() {

        seletedType = (RadioButton)findViewById(publishType.getCheckedRadioButtonId());
        BookInfo bookInfo = new BookInfo();
        bookInfo.setTitle(title.getText().toString());
        bookInfo.setDes(des.getText().toString());
        bookInfo.setCreateDate(new Date().getTime());
        bookInfo.setPublishType(seletedType.getText().toString());
        bookInfo.setLatitude(latitude);
        bookInfo.setLongitude(longitude);
        String typeNameString = typeName.getText().toString();
        if("请选择分类".equals(typeNameString)){
            typeNameString = null;
        }
        bookInfo.setBookClass(typeNameString);
        imageView.setDrawingCacheEnabled(true);
        if (imageView.getDrawable() != null) {
            bookInfo.setImg(Bitmap.createBitmap(imageView.getDrawingCache()));
        }
        imageView.setDrawingCacheEnabled(false);
        bookInfo.setLocation(location.getText().toString());
        MyDataBaseHelper.getInstance(this).storeUrineTestData(bookInfo);
    }

    private void initIndex() {
        initView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == NONE)
            return;

        if (data == null)
            return;

        // 读取相册裁剪图片
        if (requestCode == PHOTOZOOM) {
            startPhotoZoom(data.getData());
        }
        // 处理结果
        if (requestCode == PHOTORESOULT) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap photo = extras.getParcelable("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);// (0 - 100)压缩文件
                imageView.setImageBitmap(photo);
                imageView.setVisibility(View.VISIBLE);
                imageDeleteButton.setVisibility(View.VISIBLE);
            }

        }
        if (requestCode == PublishActivity.BOOKTYPEREQUEST && resultCode == RESULT_OK) {
            TextView typeName = (TextView) findViewById(R.id.typeName);
            typeName.setText(data.getStringExtra("typeName"));
        }

        if(requestCode == LOCATIONREQUEST && resultCode == RESULT_OK){
            location.setText(data.getStringExtra("selectLocation"));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
        intent.putExtra("crop", " true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTORESOULT);
    }

    /**
     * 实现定位监听接口
     */
    private class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {    //接受到定位触发事件
            city = bdLocation.getCity();
            textMapLocation.setText(bdLocation.getCity()+" "+bdLocation.getDistrict());
            String locDesc = bdLocation.getLocationDescribe();
            location.setText(locDesc.substring(1,locDesc.length()-2));
            latitude = bdLocation.getLatitude();
            longitude = bdLocation.getLongitude();
            isLocated = true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocClient.stop();
    }
}
