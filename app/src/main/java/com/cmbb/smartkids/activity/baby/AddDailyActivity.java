package com.cmbb.smartkids.activity.baby;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.adapter.PhotoAddAdapter;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.base.MApplication;
import com.cmbb.smartkids.fragment.baby.babylist.BabyListModel;
import com.cmbb.smartkids.model.photo.PhotoAdd;
import com.cmbb.smartkids.network.OkHttp;
import com.cmbb.smartkids.photopicker.PhotoPickerActivity;
import com.cmbb.smartkids.photopicker.utils.PhotoPickerIntent;
import com.cmbb.smartkids.tools.log.Log;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddDailyActivity extends MActivity {

    private RecyclerView recyclerviewContent;
    private FloatingActionButton fabAdd;

    BabyListModel mBabyListModel;

    private PhotoAddAdapter mPhotoAddAdapter;
    private ArrayList<PhotoAdd> photoUrls = new ArrayList<>();
    public int count = 10;

    private void assignViews() {
        recyclerviewContent = (RecyclerView) findViewById(R.id.recyclerview_content);
        fabAdd = (FloatingActionButton) findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count == 0) {
                    showToast("10张图片已经选满");
                } else {
                    PhotoPickerIntent intent = new PhotoPickerIntent(AddDailyActivity.this);
                    intent.setPhotoCount(count);
                    startActivityForResult(intent, REQUEST_CODE);
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_daily;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        assignViews();
        mBabyListModel = getIntent().getParcelableExtra("model");
        mPhotoAddAdapter = new PhotoAddAdapter(this, photoUrls);
        recyclerviewContent.setLayoutManager(new LinearLayoutManager(this));
        recyclerviewContent.setItemAnimator(new DefaultItemAnimator());
        recyclerviewContent.setAdapter(mPhotoAddAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_daily, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_submit) {
            uploadDaily();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public final static int REQUEST_CODE = 1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            if (data != null) {
                ArrayList<String> urls = data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
                count = count - urls.size();
                mPhotoAddAdapter.setPhotoUrls(urls);
                mPhotoAddAdapter.notifyDataSetChanged();
            }
        }
    }

    private void uploadDaily() {
        if (mPhotoAddAdapter.getPhotoContent() == null || mPhotoAddAdapter.getPhotoContent().size() == 0) {
            showToast("请添加日志内容");
            return;
        }
        showWaitDialog();
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        body.put("imageCount", mPhotoAddAdapter.getPhotoContent().size() + "");
        body.put("babyRelationId", mBabyListModel.getBabyId() + "");
        for (int i = 0; i < mPhotoAddAdapter.getPhotoContent().size(); i++) {
            body.put("context" + i, mPhotoAddAdapter.getPhotoContent().get(i).getPhotoContent());
        }
        for (Map.Entry<String, String> entry : body.entrySet()) {
            Log.i("photo", "key = " + entry.getKey() + " value = " + entry.getValue());
        }
        OkHttp.asyncPost(Constants.Baby.ADDBABYGROWING_URL, body, mPhotoAddAdapter.getPhotoContent(), new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                try {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            hideWaitDialog();
                            showToast(getResources().getString(R.string.rc_network_error));
                        }
                    });
                } catch (Exception e1) {

                }

            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    if (result.contains("1")) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hideWaitDialog();
                                showToast("提交成功");
                                finish();
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hideWaitDialog();
                                showToast("提交失败");
                            }
                        });
                    }

                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            hideWaitDialog();
                            showToast(getResources().getString(R.string.rc_network_error));
                        }
                    });
                }

            }
        });
    }
}
