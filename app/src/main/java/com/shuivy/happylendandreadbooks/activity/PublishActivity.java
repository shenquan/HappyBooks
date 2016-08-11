package com.shuivy.happylendandreadbooks.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shuivy.happylendandreadbooks.R;
import com.shuivy.happylendandreadbooks.util.ToastUtil;

import java.io.ByteArrayOutputStream;

/**
 * Created by sqhan on 2016/7/27.
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.publish);
        initIndex();
    }

    private void initView() {
        imageAddButton = (ImageButton) findViewById(R.id.add_image);
        imageDeleteButton = (ImageButton) findViewById(R.id.delete_image);
        imageView = (ImageView) findViewById(R.id.imageViewId);
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
        Button publish = (Button) findViewById(R.id.publish);
        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PublishActivity.this, BookMainActivity.class);
                startActivity(intent);
                PublishActivity.this.finish();
                ToastUtil.showToast(getApplicationContext(), "发布成功~");
            }
        });

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
}
