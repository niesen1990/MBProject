package com.cmbb.smartkids.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.adapter.ImagePreviewAdapter;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.widget.indicator.CirclePageIndicator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 项目名称：MBProject
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/11 18:56
 * <p/>
 * 用法intent传参
 * index  位置
 * data  数据源
 */
public class ImagePreviewActivity extends MActivity {
    private final String TAG = ImagePreviewActivity.class.getSimpleName();
    private ViewPager vp;
    private CirclePageIndicator indicator;
    private ImagePreviewAdapter adapter;
    private ArrayList<String> data;
    private int index = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_image_preview;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
        addListener();
    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp_image_preview);
        adapter = new ImagePreviewAdapter(this);
        data = new ArrayList<>();
        adapter.setData(data);
        vp.setOffscreenPageLimit(10);
        vp.setAdapter(adapter);
        indicator = (CirclePageIndicator) findViewById(R.id.indicator_image_preview);
        indicator.setFillColor(getResources().getColor(R.color.colorPrimary));
        indicator.setStrokeWidth(1.0f);
        indicator.setStrokeColor(getResources().getColor(R.color.color_white));
        indicator.setSnap(true);
        indicator.setViewPager(vp);
    }

    private void initData() {
        Bundle bundle = null;
        if (getIntent() != null && (bundle = getIntent().getExtras()) != null) {
            index = bundle.getInt("index", 0);
            data = bundle.getStringArrayList("data");
            if (data != null && data.size() > 0) {
                adapter.setData(this.data);
                indicator.setCurrentItem(index);
            }
        } else {
            showToast("数据出错啦...");
        }
    }

    private void addListener() {
        findViewById(R.id.fab_image_preview).setOnClickListener(this);
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                index = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    showToast("图片保存出错啦...");
                    break;
                case 1:
                    showToast("图片保存成功");
                    break;
            }
        }
    };


    @Override
    public void onClick(View v) {
        if (index < 0)
            index = 0;
        ImageView item = (ImageView) vp.getChildAt(index);
        new AsyncTask<ImageView, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                showWaitDialog();
            }

            @Override
            protected Void doInBackground(ImageView... params) {
                saveImage(params[0]);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                hideWaitDialog();
            }
        }.execute(item);
    }

    /**
     * @param item 保存图片到本地相册
     */
    private void saveImage(ImageView item) {
        // 首先保存图片
        if (item != null) {
            item.setDrawingCacheEnabled(true);
            Bitmap bmp = Bitmap.createBitmap(item.getDrawingCache());
            item.setDrawingCacheEnabled(false);
            File appDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Smartkids");
            if (!appDir.exists()) {
                appDir.mkdir();
            }
            String fileName = System.currentTimeMillis() + ".jpg";
            File file = new File(appDir, fileName);
            try {
                FileOutputStream fos = new FileOutputStream(file);
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
                fos.close();
            } catch (IOException e) {
                handler.sendEmptyMessage(0);
                e.printStackTrace();
            }
            // 最后通知图库更新
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.getAbsolutePath())));
            handler.sendEmptyMessage(1);
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showToast("图片路径出错...");
                }
            });
        }
    }

}
