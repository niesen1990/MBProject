package com.cmbb.smartkids.activity.post;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.adapter.PhotoAddAdapter;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.fragment.platelist.PlateModel;
import com.cmbb.smartkids.fragment.postlist.wonder.WonderPublicBaseModel;
import com.cmbb.smartkids.model.photo.PhotoAdd;
import com.cmbb.smartkids.network.api.ApiNetwork;
import com.cmbb.smartkids.photopicker.PhotoPickerActivity;
import com.cmbb.smartkids.photopicker.entity.PhotoDirectory;
import com.cmbb.smartkids.photopicker.utils.PhotoPickerIntent;
import com.cmbb.smartkids.tools.log.Log;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;

public class PostAddWonderActivity extends MActivity {


    private EditText etContentTitle;
    private EditText etContentDetail;
    private RecyclerView recyclerviewContent;
    private EditText etContentImage;
    //private TextView btnRule;

    private FloatingActionButton fabPublish;

    private PlateModel mPlateModel;
    private boolean flag;


    private PhotoAddAdapter mPhotoAddAdapter;
    private ArrayList<PhotoAdd> photoUrls = new ArrayList<>();
    public int count = 10;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_post_add_wonder;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        mPlateModel = getIntent().getParcelableExtra("model");
        Log.i("platemodel", "platemodel = " + mPlateModel.toString());
        flag = getIntent().getBooleanExtra("flag", false);
        assignViews();
    }

    private void assignViews() {
        etContentTitle = (EditText) findViewById(R.id.et_content_title);
        etContentDetail = (EditText) findViewById(R.id.et_content_detail);
        mPhotoAddAdapter = new PhotoAddAdapter(this, photoUrls);
        recyclerviewContent = (RecyclerView) findViewById(R.id.recyclerview_content);
        recyclerviewContent.setLayoutManager(new LinearLayoutManager(this));
        recyclerviewContent.setItemAnimator(new DefaultItemAnimator());
        recyclerviewContent.setAdapter(mPhotoAddAdapter);

        etContentImage = (EditText) findViewById(R.id.et_content_image);
        //btnRule = (TextView) findViewById(R.id.btn_rule);

        fabPublish = (FloatingActionButton) findViewById(R.id.fab_publish);
        fabPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabPublish.setClickable(false);
                uploadPost(flag);
            }
        });
    }

    /**
     * 上传帖子
     */
    private void uploadPost(final boolean isWonder) {
        showWaitDialog();
        final String mTitle = etContentTitle.getText().toString();
        final String mContext = etContentDetail.getText().toString();

        if (TextUtils.isEmpty(mTitle)) {
            showToast("请输入标题");
            hideWaitDialog();
            return;
        }
        if (TextUtils.isEmpty(mContext)) {
            showToast("请输入内容");
            hideWaitDialog();
            return;
        }


        if (isWonder) {
            ApiNetwork.uploadWonderPost(mPlateModel, mTitle, mContext, mPhotoAddAdapter.getPhotoContent(), new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            hideWaitDialog();
                            fabPublish.setClickable(true);
                            showToast("上传失败，请检查网络");
                        }
                    });
                }

                @Override
                public void onResponse(Response response) throws IOException {

                    if (response.isSuccessful()) {
                        String result = response.body().string();
                        Log.e("wonder11111", result);
                        Gson gson = new Gson();
                        final WonderPublicBaseModel data = gson.fromJson(result, WonderPublicBaseModel.class);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                fabPublish.setClickable(true);

                                if (data.getCode().equals("1")) {
                                    hideWaitDialog();
                                    setResult(RESULT_OK);
                                    finish();
                                } else {
                                    showToast("上传失败，请检查网络");
                                }
                            }
                        });
                    }
                }
            });
        } else {
            ApiNetwork.uploadAgeCityPost(mPlateModel, mTitle, mContext, getIntent().getStringExtra("areaType"), mPhotoAddAdapter.getPhotoContent(), new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            hideWaitDialog();
                            fabPublish.setClickable(true);

                            showToast("上传失败，请检查网络");
                        }
                    });
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String result = response.body().string();
                        Log.i("addPost", "addPost = " + result);
                        Gson gson = new Gson();
                        final WonderPublicBaseModel data = gson.fromJson(result, WonderPublicBaseModel.class);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                fabPublish.setClickable(true);

                                if (data.getCode().equals("1")) {
                                    hideWaitDialog();
                                    setResult(RESULT_OK);
                                    finish();
                                } else {
                                    showToast("上传失败，请检查网络");
                                }
                            }
                        });
                    }
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_post_add_wonder, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_addpic) {
            if (count == 0) {
                showToast("10张图片已经选满");
            } else {
                PhotoPickerIntent intent = new PhotoPickerIntent(this);
                intent.setPhotoCount(count);
                startActivityForResult(intent, REQUEST_CODE);
            }
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
                PhotoDirectory photoDirectory = new PhotoDirectory();
                ArrayList<String> urls = data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
                count = count - urls.size();
                mPhotoAddAdapter.setPhotoUrls(urls);
                mPhotoAddAdapter.notifyDataSetChanged();
            }
        }
    }
}
